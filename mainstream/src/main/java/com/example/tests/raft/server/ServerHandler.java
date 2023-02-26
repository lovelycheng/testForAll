package com.example.tests.raft.server;

import com.example.tests.raft.protocol.KryoProtocol;
import com.example.tests.raft.protocol.Protocol;
import com.example.tests.raft.transfer.Operation;
import com.example.tests.raft.transfer.Packet;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;
import io.netty.util.ReferenceCountUtil;

/**
 * @author chengtong
 * @date 2023/2/25 01:25
 */
public class ServerHandler extends SimpleChannelInboundHandler<Packet> {

    private final Protocol protocol = new KryoProtocol();

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Packet o) throws Exception {
        System.err.println("read");
        System.err.println(o);
    }


}
