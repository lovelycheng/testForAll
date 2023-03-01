package com.example.tests.raft.server.quorum;

import java.net.InetSocketAddress;
import java.util.ArrayList;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

import com.example.tests.raft.server.ServerCnxn;
import com.example.tests.raft.server.common.Processor;
import com.example.tests.raft.server.common.StateEnum;
import com.example.tests.raft.transfer.Packet;
import com.example.tests.raft.transfer.Votes;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;

/**
 * @author chengtong
 * @date 2023/2/28 21:57
 */
@Slf4j
@Data
public class QuorumPeer extends PeerThread{
    /**
     * 是否存活的标志
     */
    private AtomicBoolean isAlive;
    /**
     * 是否支持当前leader
     */
    private AtomicBoolean supportive = new AtomicBoolean(false);

    private static final AtomicReferenceFieldUpdater<QuorumPeer,StateEnum> fieldUpdater = AtomicReferenceFieldUpdater.newUpdater(QuorumPeer.class,StateEnum.class,"stateEnum");

    @Getter
    @Setter
    private volatile StateEnum stateEnum = StateEnum.FOLLOWER;
    /**
     * 当前地址
     */
    private final InetSocketAddress inetSocketAddress;
    /**
     * 服务连接
     */
    private final ServerCnxn cnxn;
    /**
     * 下一个log entry的索引
     */
    private AtomicInteger nextIndex;
    /**
     * jishiqi
     */
    private Timer timer;
    /**
     * 已经提交的最新的日志记录
     */
    private AtomicInteger committedIndex;

    /**
     * 任期
     */
    private int term = 0;

    private long lastConnect;

    Processor processor;

    private Integer leaderPid;

    private Integer myid;
    /**
     * 抽象的数据池
     */
    private ArrayList<Packet> data = new ArrayList<>(100);

    /**
     * hashMap
     */
    private HashMap<String,String> hashedMap = new HashMap<>();

    /**
     * 两个计时器：
     * 维持leadership，每次心跳重新计时。
     * 等待自己从follower变成candidate，超时之后变化状态
     *
     */
    public QuorumPeer(InetSocketAddress inetSocketAddress, ServerCnxn cnxn) {
        this.inetSocketAddress = inetSocketAddress;
        this.cnxn = cnxn;
        this.lastConnect = System.currentTimeMillis();
        this.isAlive = new AtomicBoolean(true);
        this.timer = Timer.electionTimer("follower超时",3, TimeUnit.SECONDS, this::startElection);

    }

    /**
     * peer 存在两种状态：
     * follower
     * candidate
     * 接受来自leader的logEntry
     */
    public Packet acceptLogEntry(Packet packet){
        return null;
    }

    public void refresh(){
        if(isAlive.get()){
            lastConnect = System.currentTimeMillis();
            timer.refresh();
        }else {
            log.info("this already dead");
        }

    }

    public Packet pong(Packet packet){
        return null;
    }

    public boolean supportElect(Votes votes){
        if(StateEnum.LEADER.equals(getStateEnum()) || !isAlive.get() || supportive.get()){
            return false;
        }else {
            return true;
        }
    }

    @Override
    public synchronized void start() {
        this.timer.start();


        super.start();
    }

    public void startElection(){
        log.info("peer:{}开始选举",myid);
        if (!fieldUpdater.compareAndSet(this,StateEnum.FOLLOWER,StateEnum.CANDIDATE)){
            log.warn("当前peer状态 not follower");
            return;
        }
        this.supportive.set(false);
    }

    // public Packet
}
