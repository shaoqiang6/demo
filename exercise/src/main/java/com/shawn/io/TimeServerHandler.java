package com.shawn.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.time.Instant;

/**
 * @author yansq
 * @date 2020/10/29
 */
public class TimeServerHandler implements Runnable {
    private Socket socket;

    public TimeServerHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        BufferedReader in = null;
        PrintWriter out = null;
        try {
            in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            out = new PrintWriter(this.socket.getOutputStream(), true);
            String currentTime = null;
            String body = null;
            int nullCount = 0;
            while (true) {
                body = in.readLine();

                if (body == null){
                    if (nullCount++ >2) {
                        break;
                    }
                    continue;
                }
                System.out.println("The time server receive order : " + body);

                currentTime = "QUERY TIME ORDER".equalsIgnoreCase(body) ?
                        Instant.now().toString() : "BAD ORDER";
                out.println(currentTime +"," + "thread id : " + Thread.currentThread().getId());
            }
        } catch (Exception e) {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            if (out != null) {
                out.close();
            }
            if (this.socket != null) {
                try {
                    this.socket.close();
                } catch (IOException e2) {
                    e2.printStackTrace();
                }
            }
        }

    }
}
