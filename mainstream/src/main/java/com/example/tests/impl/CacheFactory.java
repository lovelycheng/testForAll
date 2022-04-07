package com.example.tests.impl;

import com.example.tests.Cache;
import com.example.tests.Config;

/**
 * @author chengtong
 * @date 2020/6/17 23:59
 */
public class CacheFactory {

    public static Cache build(Config config){
        return new DefaultCacheImpl(config);
    }

}
