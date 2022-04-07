package com.example.tests;

import java.net.InetAddress;

/**
 * @author chengtong
 * @date 2020/6/17 23:27
 */
public class Config {

    boolean isRemote;

    String address;

    int port;

    public Config(boolean isRemote, String address, int port) {
        this.isRemote = isRemote;
        this.address = address;
        this.port = port;
    }


}
