package com.thesong;

import com.thesong.achieve.LRUMap;

/**
 * @Author thesong
 * @Date 2020/12/8 20:20
 * @Version 1.0
 * @Describe
 */
public class LRUMapTest {
    public static void main(String[] args) {
        LRUMap<String, String> map = new LRUMap<String, String>(5);
        map.put("1","1");
        System.out.println(map.toString());
        map.put("2","1");
        System.out.println(map.toString());
        map.put("3","1");
        System.out.println(map.toString());
        map.put("4","1");
        System.out.println(map.toString());
        map.put("5","1");
        System.out.println(map.toString());
        map.put("6","1");
        System.out.println(map.toString());
        map.put("7","1");
        map.get("3");
        System.out.println(map.toString());
        map.put("8","1");
        System.out.println(map.toString());
        map.put("9","1");
        System.out.println(map.toString());

    }

}
