package com.thesong;

import com.thesong.achieve.KnuthShuffle;

import java.util.Arrays;
import java.util.List;

/**
 * @Author thesong
 * @Date 2020/12/9 10:12
 * @Version 1.0
 * @Describe
 */
public class KnuthTest {

    public static void main(String[] args) {
        KnuthShuffle<Integer> shuffle = new KnuthShuffle();
        Integer[] ints = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        List<Integer> list = shuffle.shuffle(Arrays.asList(ints));
        System.out.println(list);
    }
}
