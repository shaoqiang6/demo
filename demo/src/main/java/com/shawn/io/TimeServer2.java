package com.shawn.io;

import com.shawn.thread.ThreadPoolHelper;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author yansq
 * @date 2020/10/29
 */
public class TimeServer2 {

    public static void main(String[] args) {
        int port = 8089;
        if (args != null && args.length > 0) {
            try {
                port = Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {
                port = 8088;
            }
        }
        ServerSocket server = null;
        try {
            server = new ServerSocket(port);
            System.out.println(" TimeServer is started in port :" + port);
            Socket accept;
            while (true) {
                accept = server.accept();
                ThreadPoolHelper.threadExecute(new TimeServerHandler(accept));
            }
        } catch (IOException e1) {

        } finally {
            if (server != null) {
                System.out.println("time server close");
                try {
                    server.close();
                } catch (IOException e2) {
                    e2.printStackTrace();
                }
            }
        }

    }
}
