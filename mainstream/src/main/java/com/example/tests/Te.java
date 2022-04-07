package com.example.tests;

import org.springframework.beans.factory.UnsatisfiedDependencyException;

/**
 * @author chengtong
 * @date 2020/6/5 17:15
 */
public class Te {

    public static void main(String[] args) {
        TestParent p = new TestChild();

        try{
            p.ss();
        }catch (UnsatisfiedDependencyException e){
            System.err.print(e.getMessage());
        }

    }


}


