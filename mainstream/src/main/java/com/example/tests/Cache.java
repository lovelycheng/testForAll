package com.example.tests;

import java.util.HashMap;

/**
 * @author chengtong
 * @date 2020/6/17 23:23
 */
public interface Cache {

    Object get(Object key);
    void set(Object key,Object value);
    void delete(Object key);

}
