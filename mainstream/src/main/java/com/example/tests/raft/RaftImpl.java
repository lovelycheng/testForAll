package com.example.tests.raft;

import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

import com.example.tests.raft.transfer.Packet;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

/**
 * @author chengtong
 * @date 2023/2/26 16:47
 */
public class RaftImpl {

    private static final AtomicReferenceFieldUpdater<RaftImpl, Integer> atomicReferenceFieldUpdater
        = AtomicReferenceFieldUpdater.newUpdater(RaftImpl.class, Integer.class, "state");
    /**
     * 1 : follower
     * 2 : candidate
     * 3 : leader
     * 4 : stopped
     */
    private volatile Integer state;

    private ConcurrentHashMap<Integer, Integer> votes = new ConcurrentHashMap<>();

    public Integer getState() {
        return state;
    }

    public void startUp() {
        Random random = new Random();
        int toSleep = random.nextInt(3000);
        try {
            Thread.sleep(toSleep);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        changeState(1);
        while (true) {
            switch (getState()) {
                case 1:
                    voteToSelf();
                case 2:
                    lead();

            }
        }

    }

    private void changeState(int i) {
        if (atomicReferenceFieldUpdater.compareAndSet(this, 0, 1)) {
            System.out.println("state changed: follower -> candidate");
        }else {
            System.out.println("state has changed before we first change state");
        }
    }

    private void lead() {

    }

    private void voteToSelf() {
        int sid = getSelfId();
        Packet packet = new Packet();
        // packet.setType(1);
        votes.put(sid, 1);
    }

    private int getSelfId() {
        return 0;
    }

}
