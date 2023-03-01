package com.example.tests.raft.config;


import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;

/**
 * @author chengtong
 * @date 2023/2/28 18:22
 */
@Data
public class Config {

    private InetSocketAddress config1 = InetSocketAddress.createUnresolved("127.0.0.1",6970);
    private InetSocketAddress config2 = InetSocketAddress.createUnresolved("127.0.0.1",6971);
    private InetSocketAddress config3 = InetSocketAddress.createUnresolved("127.0.0.1",6972);
    private InetSocketAddress local;
    private List<InetSocketAddress> others = new ArrayList<>();
    public Config(int index) throws Exception {
        if(index == 1){
            this.local = config1;
            others.add(config2);
            others.add(config3);
        }else if(index == 2){
            this.local = config2;
            others.add(config1);
            others.add(config3);
        }else if(index == 3){
            this.local = config3;
            others.add(config2);
            others.add(config1);
        }else {
            throw new Exception();
        }
    }


}
