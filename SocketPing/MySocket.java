package org.unibl.etf.SocketPing;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class MySocket {
    public static void main(String[] args) {
        try (Socket socket = new Socket(InetAddress.getByName("127.0.0.1"),5939);
             BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()))) {
            String message = "ping";
            pw.println(message);
            System.out.println("Sent message: " + message);
            String response = br.readLine();
            System.out.println("Received message: " + response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
