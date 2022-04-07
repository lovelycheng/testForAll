package com.example.tests;

import java.util.ArrayList;
import java.util.TreeMap;

import static java.lang.System.err;

/**
 * @author chengtong
 * @date 2020/7/8 14:47
 */
public class JustTest {

    public static void main(String[] args) {

        // creating tree map
        TreeMap<Integer, String> treemap = new TreeMap<Integer, String>();

        // populating tree map
        treemap.put(2, "two");
        treemap.put(1, "one");
        treemap.put(3, "three");
        treemap.put(6, "six");
        treemap.put(5, "five");

        System.out.println("Checking first entry");
        System.out.println("First entry is: "+ treemap.firstEntry());
        System.out.println("First entry is: "+ treemap.firstKey());

    }
}
