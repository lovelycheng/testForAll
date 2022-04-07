package com.example.tests;

import org.springframework.beans.factory.UnsatisfiedDependencyException;

import java.util.HashMap;
import java.util.IllegalFormatException;

/**
 * @author chengtong
 * @date 2020/6/5 17:04
 */
public class TestParent {

    protected void ss() throws UnsatisfiedDependencyException {
    }

    public static void main(String[] args) {

        HashMap<Integer,Object> a = new HashMap<>(16);

        a.put(0,1);
        a.put(1,2);
        a.put(2,1);
        a.put(3,1);
        a.put(4,1);
        a.put(5,1);
        a.put(6,1);
        a.put(7,1);
        a.put(8,1);
        a.put(9,1);
        a.put(10,1);
        a.put(11,1);
        a.put(12,1);
        a.put(13,1);


    }
}
