package com.example.tests.raft.transfer;

/**
 * @author chengtong
 * @date 2023/2/25 15:48
 */
public class Votes {
    private int pid;
    private int leaderPid;
    private int count;

    @Override
    public String toString() {
        return "Votes{" + "pid=" + pid + ", leaderPid=" + leaderPid + ", count=" + count + '}';
    }
}
