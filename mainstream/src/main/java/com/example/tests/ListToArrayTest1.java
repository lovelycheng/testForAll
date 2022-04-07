package com.example.tests;

import java.util.ArrayList;

/**
 * @author chengtong
 * @date 2020/5/9 07:57
 */
public class ListToArrayTest1 {

    private static boolean  ss;

    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();

        list.add("get");
        list.add("-s");
        list.add("/lock");

        String[] s= list.toArray(new String[0]);

        System.err.print(ss);

    }

}
