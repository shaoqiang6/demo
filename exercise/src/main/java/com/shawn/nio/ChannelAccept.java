package com.shawn.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;

public class ChannelAccept {

    private static String greeting = "I must be going ...";

    /**
     * 启动main方法之后使用这个命令连接:
     * telnet 127.0.0.1 1234
     * @param args
     */
    public static void main(String[] args) {
        int port = 1234;
        if(args != null && args.length > 0) {
            port = Integer.parseInt(args[0]);
        }
        ByteBuffer buffer = ByteBuffer.wrap(greeting.getBytes(StandardCharsets.UTF_8));
        try(ServerSocketChannel ssc = ServerSocketChannel.open()) {
            ssc.bind(new InetSocketAddress(port));
            ssc.configureBlocking(false);
            while(true) {
                System.out.println("waiting for connection");
                final SocketChannel accept = ssc.accept();
                if (accept == null) {
                    TimeUnit.SECONDS.sleep(1L);
                } else {
                    System.out.println("Incoming connection from: " + accept.getRemoteAddress());
                    buffer.rewind();
                    accept.write(buffer);
                    accept.close();
                }
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
