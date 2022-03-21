package com.shawn.nio;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Marketing {

    private static String path = "./balabala.txt";
    public static void main(String[] args) {

        try (FileChannel channel = new FileOutputStream(path).getChannel()) {
            ByteBuffer[] bufs = utterBuffer(10);
//            final MappedByteBuffer map = channel.map(FileChannel.MapMode.PRIVATE, 0, channel.size());

            while(channel.write(bufs) > 0) {

            }
            System.out.println("数据同步到 文件:" + path);

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private static ByteBuffer[] utterBuffer(int resp) {
        List<ByteBuffer> list = new LinkedList<>();
        for (int i = 0; i < resp; i++) {
            list.add(randomPick(col1, " "));
            list.add(randomPick(col2, "\n"));
            list.add(randomPick(col3, " "));

        }
        ByteBuffer[] byteBuffers = new ByteBuffer[list.size()];
        return list.toArray(byteBuffers);
    }
    private static Random random = new Random();

    private static ByteBuffer randomPick(String[] col, String suffix) {
        String string = col[random.nextInt(col.length)];
        int total = string.length() + suffix.length();
        ByteBuffer byteBuffer = ByteBuffer.allocate(total);
        byteBuffer.put(string.getBytes(StandardCharsets.UTF_8));
        byteBuffer.put(suffix.getBytes(StandardCharsets.UTF_8));
        // 切换读写
        System.out.print(string);
        System.out.print(suffix);
        byteBuffer.flip();
        return byteBuffer;
    }


    private static String [] col1 = {
            "Aggregate", "Enable", "Leverage",
            "Facilitate", "Synergize", "Repurpose",
            "Strategize", "Reinvent", "Harness"
    };
    private static String [] col2 = {
            "cross-platform", "best-of-breed", "frictionless",
            "ubiquitous", "extensible", "compelling",
            "mission-critical", "collaborative", "integrated"
    };
    private static String [] col3 = {
            "methodologies", "infomediaries", "platforms",
            "schemas", "mindshare", "paradigms",
            "functionalities", "web services", "infrastructures"
    };
}
