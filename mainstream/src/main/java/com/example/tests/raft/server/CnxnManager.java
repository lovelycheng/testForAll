package com.example.tests.raft.server;

import java.net.InetAddress;
import java.net.InetSocketAddress;

import com.example.tests.raft.protocol.Serializer;
import com.example.tests.raft.transfer.Packet;

import io.netty.channel.Channel;
import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.Attribute;
import io.netty.util.AttributeKey;
import io.netty.util.ReferenceCountUtil;
import io.netty.util.concurrent.DefaultEventExecutor;
import lombok.extern.java.Log;

/**
 * @author chengtong
 * @date 2023/2/27 00:05
 */
@Log
public class CnxnManager {
    /**
     * 用于存放所有active的channel
     */
    private final ChannelGroup allChannels = new DefaultChannelGroup("serverCnxns", new DefaultEventExecutor());

    /**
     * 需要一个处理器，处理读取和写入、在channel active时加入 allChannels；inactive 移除；
     * 使用默认的双工通信的处理器
     */
    @ChannelHandler.Sharable
    class Handler extends ChannelDuplexHandler {

        @Override
        public void channelActive(ChannelHandlerContext ctx) throws Exception {
            log.info("channel active");
            final Channel channel = ctx.channel();

            InetAddress inetAddress = ((InetSocketAddress) channel.remoteAddress()).getAddress();
            log.info(inetAddress.getHostAddress() + " connected");
            allChannels.add(channel);
            ServerCnxn cnxn =new ServerCnxn();
            //channel CNXN 的绑定关系
            channel.attr(AttributeKey.valueOf("ChannelKey")).set(cnxn);

        }

        @Override
        public void channelInactive(ChannelHandlerContext ctx) throws Exception {
            log.info("channel inactive");
            final Channel channel = ctx.channel();
            allChannels.remove(channel);
        }

        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            try {
                System.err.println("read");
                System.err.println(msg);
                ctx.fireChannelRead(msg);
            } finally {
                ReferenceCountUtil.release(msg);
            }
        }

        @Override
        public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
            super.channelReadComplete(ctx);
        }
    }

}
