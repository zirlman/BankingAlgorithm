package org.unibl.etf.Server;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    //  Serverski proces koji prihvata klijentski zahtejv preko socket-a na portu 8080 i odmah raskida konekciju
    static int port = 8080;
    public static void main(String[] args) {
        try (ServerSocket ss = new ServerSocket(port)) {
            while (true) {
                Socket clientSocket = ss.accept();
                BufferedReader br = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                String request = br.readLine();
                if (request != null)
                    clientSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
// Ispis Hello World na socket karakter po karakter
//    static int port = 6060;
//    public static void main(String[] args) {
//        try (ServerSocket ss = new ServerSocket(port)) {
//            while (true) {
//                Socket clientSocket = ss.accept();
//                PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream())));
//                String response = "Hello World!";
//                pw.println(response);
//                pw.close();
//                clientSocket.close();
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
}
