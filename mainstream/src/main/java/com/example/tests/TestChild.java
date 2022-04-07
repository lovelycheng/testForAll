package com.example.tests;

/**
 * @author chengtong
 * @date 2020/6/5 17:04
 */
public class TestChild extends TestParent{

    @Override
    protected void ss() throws RuntimeException  {
        super.ss();
        throw new RuntimeException();
    }
}
