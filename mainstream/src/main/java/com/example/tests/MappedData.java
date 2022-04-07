package com.example.tests;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * @author chengtong
 * @date 2022/3/17 19:18
 */
public class MappedData {

    static String name = "/Users/chengtong/Downloads/urldatas.txt";
    static String ds = "/Users/chengtong/Downloads/mch.txt";

    public static void main(String[] args) {

        try {
            List<String> list = Files.readAllLines(Paths.get(name));
            for (String url:list){
                System.err.println(url);
                for(String d:url.split("\\s")){
                    System.err.println(d);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
