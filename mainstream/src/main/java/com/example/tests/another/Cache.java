package com.example.tests.another;

import com.example.tests.Config;

/**
 * @author chengtong
 * @date 2020/6/18 00:10
 */
public class Cache implements NormalCache,DataSourceChangeableCache {


    @Override
    public void changeDataSource(Config config) {

    }

    @Override
    public Object get(Object key) {
        return null;
    }

    @Override
    public void set(Object key, Object value) {

    }

    @Override
    public void delete(Object key) {

    }
}
