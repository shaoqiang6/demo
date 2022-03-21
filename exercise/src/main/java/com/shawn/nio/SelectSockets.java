package com.shawn.nio;


import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;

/**
 * 一个简单的服务器
 */
public class SelectSockets {

    public static int PORT = 1234;

    public static void main(String[] args) throws Exception {
        SelectSockets selectSockets = new SelectSocketThreadPool();
        selectSockets.go(args);
    }

    public void go(String[] args) throws Exception {
        int port = PORT;
        if (args != null && args.length > 0) {
            port = Integer.parseInt(args[0]);
        }
        System.out.println("listen port: " + port);
        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.configureBlocking(false);
        ServerSocket socket = ssc.socket();
        socket.bind(new InetSocketAddress(port));

        Selector selector = Selector.open();
        ssc.register(selector, SelectionKey.OP_ACCEPT);
        int i = 0;
        while(true) {
            int n = selector.select();
            System.out.println(i++);
            if (n == 0) {
                continue;
            }
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                if (key.isAcceptable()) {
                    ServerSocketChannel channel = (ServerSocketChannel) key.channel();
                    SocketChannel socketChannel = channel.accept();

                    registerChannel(selector, socketChannel, SelectionKey.OP_READ);
                    sayHello(socketChannel);
                }
                if (key.isReadable()) {
                    System.out.println("isReadable ");
                    readFromSocket(key);
                }
                iterator.remove();
            }
            System.out.println(111);
        }


    }

    protected void readFromSocket(SelectionKey key) throws IOException, InterruptedException {
        SocketChannel socketChannel = (SocketChannel) key.channel();
        buffer.clear();
        int count = 0;
        while((count = socketChannel.read(buffer)) > 0) {
            buffer.flip();
            Thread.sleep(1000L);
//            System.out.println("read ...");
            // 这个地方取出缓冲区的内容后可以做具体的事情, 现在的代码是无聊的
            while (buffer.hasRemaining()) {
                socketChannel.write(buffer);
            }
            buffer.clear();
        }
        if (count < 0) {
            socketChannel.close();
        }
    }

    private ByteBuffer buffer = ByteBuffer.allocate(1024);

    protected void sayHello(SocketChannel channel) throws IOException {
        buffer.clear();
        buffer.put("hi here \r\n".getBytes(StandardCharsets.UTF_8));
        buffer.flip();
        channel.write(buffer);
    }

    protected void registerChannel(Selector selector, SelectableChannel channel, int ops) throws Exception {
        if (channel == null) {
            return;
        }
        channel.configureBlocking(false);
        channel.register(selector, ops);
    }


}
