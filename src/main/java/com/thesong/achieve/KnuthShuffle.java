package com.thesong.achieve;


import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * @Author thesong
 * @Date 2020/12/9 9:29
 * @Version 1.0
 * @Describe
 */
public class KnuthShuffle<E> {

    private static Random r = new Random();

    private static int rand(int min, int max) {
        return r.nextInt(max - min) + min;
    }

    public List<E> shuffle(List<E> list) {
        if (list == null) {
            throw new NullPointerException();
        }
        for (int i = list.size(); i > 0; i--) {
            E tmp = list.get(i-1);
            int swapIndex = rand(0, list.size());
            list.set(i-1, list.get(swapIndex));
            list.set(swapIndex, tmp);
        }
        return list;
    }

}
