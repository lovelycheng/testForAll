package com.example.tests;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.StandardSocketOptions;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author chengtong
 * @date 2020/6/11 17:05
 *
 * 一个简单的回文nio服务器
 */
public class NIOServer {

    private static ConcurrentHashMap<Channel,ByteBuffer> byteBufferMap = new ConcurrentHashMap<>();

    public static void main(String[] args) {

        Selector selector = null;
        try {
             selector = Selector.open();

            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.configureBlocking(false);
            serverSocketChannel.bind(new InetSocketAddress(8080));
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

            while (true) {// 至少有一个channel准备好了 '

                selector.select();

                Set<SelectionKey> keys = selector.selectedKeys();//遍历所有channel

                Iterator<SelectionKey> selectionKeyIterator = keys.iterator();

                while (selectionKeyIterator.hasNext()) {
                    SelectionKey key = selectionKeyIterator.next();
                    selectionKeyIterator.remove();

                    if(key.isValid()){
                        if (key.isAcceptable()) {
                            ServerSocketChannel channel = (ServerSocketChannel) key.channel();

                            channel.configureBlocking(false);

                            SocketChannel socketChannel = channel.accept();
                            socketChannel.configureBlocking(false);

                            socketChannel.register(selector,SelectionKey.OP_READ);

                            byteBufferMap.put(socketChannel,ByteBuffer.allocateDirect(80));
                        }
                        else if (key.isReadable()) {
                            SocketChannel socketChannel = (SocketChannel) key.channel();
                            ByteBuffer byteBuffer = byteBufferMap.get(socketChannel);
                            socketChannel.configureBlocking(false);
                            socketChannel.setOption(StandardSocketOptions.SO_KEEPALIVE, true);
                            socketChannel.setOption(StandardSocketOptions.SO_REUSEADDR, true);
                            socketChannel.setOption(StandardSocketOptions.TCP_NODELAY, true);
                            int data = socketChannel.read(byteBuffer);

                            if(data == -1){
                                System.err.println("connect read disable.");
                                socketChannel.close();
                                byteBufferMap.remove(socketChannel);
                            }

                            if(key.isValid()){
                                key.interestOps(SelectionKey.OP_WRITE);
                            }

                        }else
                        if(key.isWritable()){
                            SocketChannel socketChannel = (SocketChannel) key.channel();
                            ByteBuffer byteBuffer = byteBufferMap.get(socketChannel);
                            byteBuffer.flip();
                            process(byteBuffer);
                            socketChannel.write(byteBuffer);
                            if(!byteBuffer.hasRemaining()){
                                byteBuffer.compact();
                                System.err.println("connect write enable.");
                                key.interestOps(SelectionKey.OP_READ);
                            }
                        }
                    }
                    byteBufferMap.keySet().removeIf(ss -> !ss.isOpen());
                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(selector != null){
                try {
                    selector.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static ByteBuffer process(ByteBuffer byteBuffer){

        int limit = byteBuffer.limit();
        byte[] bytes = new byte[limit];
        for(int i=0,j=limit-1;i<limit;i++,j--){
         bytes[j] = (byte) (Character.isLetter(byteBuffer.get(i))?byteBuffer.get(i) ^ ' ':byteBuffer.get(i));
        }
        for(int i=0;i<limit;i++){
            byteBuffer.put(i,bytes[i]);
        }
        byteBuffer.limit(limit);
        byteBuffer.position(0);


        return byteBuffer;
    }

}
