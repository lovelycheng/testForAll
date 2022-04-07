package com.example.tests.another;

/**
 * @author chengtong
 * @date 2020/6/18 00:11
 */
public interface NormalCache {

    Object get(Object key);
    void set(Object key,Object value);
    void delete(Object key);


}
