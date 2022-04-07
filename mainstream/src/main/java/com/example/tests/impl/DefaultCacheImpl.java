package com.example.tests.impl;

import com.example.tests.Cache;
import com.example.tests.Config;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author chengtong
 * @date 2020/6/17 23:24
 */
public class DefaultCacheImpl implements Cache, CacheDataSourceSwitcher {

    ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();

    Config config;

    DefaultCacheImpl(Config remoteConfig){
        this.config = remoteConfig;
    }

    @Override
    public Object get(Object key) {
        return concurrentHashMap.get(key);
    }

    @Override
    public void set(Object key, Object value) {
        concurrentHashMap.put(key,value);
    }

    @Override
    public void delete(Object key) {
        concurrentHashMap.remove(key);
    }

    @Override
    public void doSwitch() {
        if(config != null){

        }
    }


}
