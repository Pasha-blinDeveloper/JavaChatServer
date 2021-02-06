package com.company;

import java.io.*;
import java.net.Socket;

public class ClientWriter extends Thread{
    BufferedWriter writer;
    BufferedReader reader;
    Socket socket;
    int port;
    public ClientWriter(int port)  {
        this.port = port;
    }
    @Override
    public void run() {
        try {
            socket = new Socket("192.168.42.226",port);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        reader = new BufferedReader(new InputStreamReader(System.in));
        String message = "null";
        String nickname = "null";
        System.out.println("Nickname >>>: ");

        try {
            nickname = reader.readLine();
            writer.write(nickname);
            writer.flush();
            writer.newLine();
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        while (message != "...break"){
            try {
                message = reader.readLine();
                writer.write(message);
                writer.flush();
                writer.newLine();
                writer.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
