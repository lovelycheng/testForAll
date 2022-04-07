package com.example.tests;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.HashMap;
import java.util.Objects;

/**
 * @author chengtong
 * @date 2020/5/7 15:03
 */
public class MainClass {

    private long x;
    private long y;

    HashMap hashMap = new HashMap(16);

    public static void main(String[] args) {

        try {
            RandomAccessFile raf = new RandomAccessFile(new File("/Users/chengtong/demo/src/main/resources/test.txt"),
                    "rw");
            long l = raf.length();
            System.err.println(l);
            byte[] buffer = new byte[(int)l];

            System.err.println(raf.read(buffer));

            System.err.println(new String(buffer));

            raf.setLength(6);

            buffer = new byte[(int)l];

            System.err.println(raf.read(buffer));

            System.err.println(new String(buffer));

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MainClass)) return false;
        MainClass mainClass = (MainClass) o;
        return x == mainClass.x &&
                y == mainClass.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
