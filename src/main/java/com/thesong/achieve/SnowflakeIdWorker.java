package com.thesong.achieve;

import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.SystemUtils;

import java.net.Inet4Address;
import java.net.UnknownHostException;

/**
 * @Author thesong
 * @Date 2020/12/9 14:59
 * @Version 1.0
 * @Describe 雪花算法id生成 workid 采用 ip datacenterid采用hostname 分别对32取余数
 */


public class SnowflakeIdWorker {

    private final static long START_STMP = 1607497567L; // 开始时间戳

    private final static long MACHINE_BIT = 5L;   //机器标识占用的位数
    private final static long DATACENTER_BIT = 5L; //数据中心所占的位数
    private final static long SEQUENCE_BIT = 12L; //序列号占用的位数

    private final static long MAX_DATACENTER_NUM = -1L ^ (-1L << DATACENTER_BIT);
    private final static long MAX_MACHINE_NUM = -1L ^ (-1L << MACHINE_BIT);
    private final static long MAX_SEQUENCE = -1L ^ (-1L << SEQUENCE_BIT);

    private final static long MACHINE_LEFT = SEQUENCE_BIT; //
    private final static long DATACENTER_LEFT = SEQUENCE_BIT + MACHINE_BIT;
    private final static long TIMESTMP_LEFT = DATACENTER_LEFT + DATACENTER_BIT;

    private long datacenterId;  //数据中心
    private long machineId;     //机器标识
    private long sequence = 0L; //序列号
    private long lastStmp = -1L;//上一次时间戳

    private static SnowflakeIdWorker snowflakeIdWorker;

    static {
        snowflakeIdWorker = new SnowflakeIdWorker();
    }

    public SnowflakeIdWorker() {
        long datacenterId = getDataCenterId();
        long machineId = getMachineId();
        if (datacenterId > MAX_DATACENTER_NUM || datacenterId < 0) {
            throw new IllegalArgumentException("datacenterId can't be greater than "+MAX_DATACENTER_NUM+" or less than 0");
        }
        if (machineId > MAX_MACHINE_NUM || machineId < 0) {
            throw new IllegalArgumentException("machineId can't be greater than "+MAX_MACHINE_NUM+" or less than 0");
        }
        this.datacenterId = datacenterId;
        this.machineId = machineId;
    }

    public synchronized long nextId() {
        long currStmp = getNewstmp();
        if (currStmp < lastStmp) {
            throw new RuntimeException("Clock moved backwards.  Refusing to generate id");
        }
        if (currStmp == lastStmp) {
            //相同毫秒内，序列号自增
            sequence = (sequence + 1) & MAX_SEQUENCE;
            //同一毫秒的序列数已经达到最大
            if (sequence == 0L) {
                //循环获取多几次，尽可能地避免不可能的可能
                for(int i = 0; i< 100; i ++){
                    currStmp = getNextMill();
                    if (currStmp != lastStmp){
                        break;
                    }
                }
            }
        } else {
            //不同毫秒内，序列号置为0
            sequence = 0L;
        }

        lastStmp = currStmp;

        return (currStmp - START_STMP) << TIMESTMP_LEFT //时间戳部分
                | datacenterId << DATACENTER_LEFT       //数据中心部分
                | machineId << MACHINE_LEFT             //机器标识部分
                | sequence;                             //序列号部分
    }

    private static Long getMachineId(){
        try {
            String hostAddress = Inet4Address.getLocalHost().getHostAddress();
            int[] ints = StringUtils.toCodePoints(hostAddress);
            int sums = 0;
            for(int b : ints){
                sums += b;
            }
            return (long)(sums % 32);
        } catch (UnknownHostException e) {
            // 如果获取失败，则使用随机数备用
            return RandomUtils.nextLong(0,31);
        }
    }

    private static Long getDataCenterId(){
        String hostName = SystemUtils.getHostName();
        int[] ints = StringUtils.toCodePoints(hostName);
        int sums = 0;
        for (int i: ints) {
            sums += i;
        }
        return (long)(sums % 32);
    }

    private long getNextMill() {
        long mill = getNewstmp();
        while (mill <= lastStmp) {
            mill = getNewstmp();
        }
        return mill;
    }

    private long getNewstmp() {
        return System.currentTimeMillis();
    }

    public static synchronized Long generateId(){
        return snowflakeIdWorker.nextId();
    }
}
