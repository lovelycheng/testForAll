package com.example.tests;

import javax.annotation.security.RunAs;
import java.io.IOException;
import java.net.StandardSocketOptions;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * @author chengtong
 * @date 2020/6/16 19:47
 */
public class MultipledSelectorNIOServer {

    private static Map<SocketChannel, ByteBuffer> SOCKET_BUFFER = new ConcurrentHashMap<>();

    public static void main(String[] args) throws IOException, InterruptedException {

        Thread thread = new Thread(new DemoThread());
        Thread thread1 = new Thread(new DemoThread());
        Thread thread2 = new Thread(new DemoThread());
        Thread thread3 = new Thread(new DemoThread());

        thread.start();
        thread1.start();
        thread2.start();
        thread3.start();

        Thread a = new Thread(new WakeUper());

        a.start();
    }

    static class WakeUper extends PTHread{

        WakeUper() throws IOException {
        }

        @Override
        public void run() {
            selector.wakeup();
        }
    }

    static abstract class PTHread implements Runnable{
       protected final Selector selector;

        PTHread() throws IOException {
            this.selector = Selector.open();
        }
    }

    static  class DemoThread extends PTHread{

        DemoThread() throws IOException {
        }

        @Override
        public void run() {
            try {
                selector.select();
                System.err.print("waked up by selector.wakeup");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }



    class SelectorThread implements Runnable {

        int id;
        LinkedBlockingDeque<SocketChannel> acceptedSocket = new LinkedBlockingDeque<>();
        Selector selector;

        public SelectorThread(int id) throws IOException {
            this.id = id;
            this.selector = Selector.open();
        }

        @Override
        public void run() {
            while (true) {
                try {
                    selector.select();

                    Set<SelectionKey> keys = selector.selectedKeys();


                    Iterator<SelectionKey> selectionKeyIterator = keys.iterator();

                    while (selectionKeyIterator.hasNext()) {
                        SelectionKey key = selectionKeyIterator.next();
                        selectionKeyIterator.remove();

                        if (key.isValid()) {
                            if (key.isAcceptable()) {
                                ServerSocketChannel channel = (ServerSocketChannel) key.channel();

                                channel.configureBlocking(false);

                                SocketChannel socketChannel = channel.accept();
                                socketChannel.configureBlocking(false);

                                socketChannel.register(selector, SelectionKey.OP_READ);

                                SOCKET_BUFFER.put(socketChannel, ByteBuffer.allocateDirect(80));
                            } else if (key.isReadable()) {
                                SocketChannel socketChannel = (SocketChannel) key.channel();
                                ByteBuffer byteBuffer = SOCKET_BUFFER.get(socketChannel);
                                socketChannel.configureBlocking(false);
                                socketChannel.setOption(StandardSocketOptions.SO_KEEPALIVE, true);
                                socketChannel.setOption(StandardSocketOptions.SO_REUSEADDR, true);
                                socketChannel.setOption(StandardSocketOptions.TCP_NODELAY, true);
                                int data = socketChannel.read(byteBuffer);

                                if (data == -1) {
                                    System.err.println("connect read disable.");
                                    socketChannel.close();
                                    SOCKET_BUFFER.remove(socketChannel);
                                }

                                if (key.isValid()) {
                                    key.interestOps(SelectionKey.OP_WRITE);
                                }

                            } else if (key.isWritable()) {
                                SocketChannel socketChannel = (SocketChannel) key.channel();
                                ByteBuffer byteBuffer = SOCKET_BUFFER.get(socketChannel);
                                byteBuffer.flip();
                                process(byteBuffer);
                                socketChannel.write(byteBuffer);
                                if (!byteBuffer.hasRemaining()) {
                                    byteBuffer.compact();
                                    System.err.println("connect write enable.");
                                    key.interestOps(SelectionKey.OP_READ);
                                }
                            }
                        }
                        SOCKET_BUFFER.keySet().removeIf(ss -> !ss.isOpen());
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
        }
    }

    private static ByteBuffer process(ByteBuffer byteBuffer) {

        int limit = byteBuffer.limit();
        byte[] bytes = new byte[limit];
        for (int i = 0, j = limit - 1; i < limit; i++, j--) {
            bytes[j] = (byte) (Character.isLetter(byteBuffer.get(i)) ? byteBuffer.get(i) ^ ' ' : byteBuffer.get(i));
        }
        for (int i = 0; i < limit; i++) {
            byteBuffer.put(i, bytes[i]);
        }
        byteBuffer.limit(limit);
        byteBuffer.position(0);


        return byteBuffer;
    }


}
