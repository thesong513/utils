package com.thesong;

import com.thesong.achieve.SnowflakeIdWorker;

/**
 * @Author thesong
 * @Date 2020/12/9 15:50
 * @Version 1.0
 * @Describe
 */
public class SnowflakerTest {

    public static void main(String[] args) {

        long start = System.currentTimeMillis();
        for (int i = 0; i < 200000; i++) {
            System.out.println(SnowflakeIdWorker.generateId());
        }
        System.out.println(System.currentTimeMillis() - start);

    }
}
