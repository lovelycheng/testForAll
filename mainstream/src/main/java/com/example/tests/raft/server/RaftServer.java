package com.example.tests.raft.server;

import com.example.tests.raft.server.follower.codec.OrderFrameDecoder;
import com.example.tests.raft.server.follower.codec.OrderFrameEncoder;
import com.example.tests.raft.server.follower.codec.OrderProtocolDecoder;
import com.example.tests.raft.server.follower.codec.OrderProtocolEncoder;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

/**
 * @author chengtong
 * @date 2023/2/25 19:36
 */
public class RaftServer {

    private CnxnManager cnxnManager = new CnxnManager();




    public static void main(String[] args) {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            //创建服务端的启动对象，设置参数
            ServerBootstrap bootstrap = new ServerBootstrap();
            //设置两个线程组boosGroup和workerGroup
            bootstrap.group(bossGroup, workerGroup)
                .handler(new LoggingHandler(LogLevel.INFO))
                //设置服务端通道实现类型
                .channel(NioServerSocketChannel.class)
                //设置线程队列得到连接个数
                .option(ChannelOption.SO_BACKLOG, 128)
                //设置保持活动连接状态
                .childOption(ChannelOption.SO_KEEPALIVE, true)
                //使用匿名内部类的形式初始化通道对象
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) {
                        //给pipeline管道设置处理器
                        socketChannel.pipeline()
                            .addLast(new OrderFrameDecoder())
                            .addLast(new OrderFrameEncoder())
                            .addLast(new OrderProtocolDecoder())
                            .addLast(new OrderProtocolEncoder())
                            .addLast(new ServerHandler());
                    }
                });//给workerGroup的EventLoop对应的管道设置处理器
            //绑定端口号，启动服务端
            ChannelFuture channelFuture = bootstrap.bind(6699)
                .sync();
            //对关闭通道进行监听
            channelFuture.channel()
                .closeFuture()
                .sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}