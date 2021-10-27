package com.shawn.gateway;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author yansq
 * @date 2020/11/1
 */
public class GatewayServer {

    public static void main(String[] args) {
        int port = 8888;
        ServerSocket server = null;
        while (true) {
            try {
                server = new ServerSocket(port);
                Socket socket = server.accept();
//            String response = HttpClient.get("http://localhost:8081");
                String response = "hello nio";
                service(socket, response);
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

    private static void service(Socket  socket, String response) {
        try {
            PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);
            printWriter.println("HTTP/1.1 200 OK");
            printWriter.println("Content-type:text/html;charset=utf-8");
            printWriter.println("Content-Length:" + response.getBytes().length);
            printWriter.println("QUERY TIME ORDER");
//            printWriter.println();
            printWriter.write(response);
            printWriter.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
