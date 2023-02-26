package com.example.tests.raft.transfer;

import lombok.Data;

/**
 * @author chengtong
 * @date 2023/2/25 15:57
 */
@Data
public class Operation {

    private int type;
    private String key;
    private String value;

    @Override
    public String toString() {
        return "Operation{" + "type=" + type + ", key='" + key + '\'' + ", value='" + value + '\'' + '}';
    }
}
