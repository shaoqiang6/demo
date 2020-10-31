package com.shawn.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * @author yansq
 * @date 2020/10/29
 */
public class TimeClient {
    public static void main(String[] args) {
//        int port = 8087;
        int port = 8089;
        long time = 0;
        for (int j = 0;j<100;j++) {
            long start = System.currentTimeMillis();
            for (int i = 0; i < 100; i++) {
                req(port);
            }
            long cost = System.currentTimeMillis() - start;
            System.out.println(String.format("耗时: %d", cost));
            time +=cost;
        }
        System.out.println("avg time :" + (time/100));

    }

    private static void req(int port) {
        Socket socket;
        BufferedReader in = null;
        PrintWriter out = null;
        try {
            socket = new Socket("127.0.0.1", port);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
            out.println("QUERY TIME ORDER");
            System.out.println("send order 2 server succeed");
            String resp = in.readLine();
            System.out.println("Now is : " + resp);
        } catch (Exception e) {

        } finally {
            if (out != null) {
                out.close();
            }
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e2) {

                }
            }
        }
    }
}
