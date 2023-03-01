package com.example.tests.raft.server.common.processimpl;

import com.example.tests.raft.server.common.Processor;
import com.example.tests.raft.server.quorum.QuorumPeer;
import com.example.tests.raft.transfer.Packet;
import com.example.tests.raft.transfer.TypeCode;
import com.example.tests.raft.transfer.Votes;

import lombok.extern.slf4j.Slf4j;

/**
 * @author chengtong
 * @date 2023/3/1 01:51
 */
@Slf4j
public class VoteProcessor implements Processor {

    private final QuorumPeer quorumPeer;

    Processor next;

    public VoteProcessor(QuorumPeer quorumPeer, Processor next) {
        this.quorumPeer = quorumPeer;
        this.next = next;
    }

    @Override
    public void process(Packet.Body body) {
        if (!body.getType()
            .equals(TypeCode.VOTES)) {
            next.process(body);
        } else {
            Votes votes = (Votes) body.getData();
            if(quorumPeer.supportElect(votes)){
                int term = votes.getTerm();
                if (term < this.quorumPeer.getTerm()){
                    log.info("对方任期小于自己，不接受选举信息");
                    //todo 返回false
                }
                int nextIndex = votes.getLastLogIndex();
                if(nextIndex < this.quorumPeer.getNextIndex().get()) {
                    //todo 返回false
                }
                //todo 修改自己的votefor
                log.info("");
            }
        }
    }
}
