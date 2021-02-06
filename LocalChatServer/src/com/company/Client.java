package com.company;

import java.io.*;
import java.net.ConnectException;
import java.net.Socket;


public class Client {
    public static void main(String[] args) throws IOException {
        Socket Firstsocket;
        Socket mainSocket;
        int mainPort = -1;
        BufferedReader reader;
        Firstsocket = new Socket("192.168.42.226", 6745);
        reader = new BufferedReader(new InputStreamReader(Firstsocket.getInputStream()));
        while (mainPort == -1) {
            mainPort = reader.read();
            System.out.println("Your port is >>>: " + mainPort);
        }
        Firstsocket.close();
        ClientWriter writer = new ClientWriter(mainPort);
        writer.start();
        System.out.println("<<< YOU CONNECTED >>>");
    }
}

