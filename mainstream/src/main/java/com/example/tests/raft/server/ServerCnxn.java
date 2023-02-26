package com.example.tests.raft.server;

import io.netty.channel.Channel;
import lombok.Data;

/**
 * @author chengtong
 * @date 2023/2/27 05:16
 */
@Data
public class ServerCnxn {

    private Channel channel;
    private CnxnManager cnxnManager;

    void process(){

    }
}
