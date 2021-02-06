package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class ClientHandler extends Thread {
    int port;
    ServerSocket socket;
    public ClientHandler(int port){
        this.port = port;
    }
    @Override
    public void run() {
        super.run();
        try {
            String nickname;
            InetAddress address = InetAddress.getLocalHost();
            socket = new ServerSocket(this.port,1,address);
            Socket socketClient = socket.accept();
            System.out.println("Connected port>>> " + this.port);
            BufferedReader reader = new BufferedReader(new InputStreamReader(socketClient.getInputStream()));
            nickname = reader.readLine();
            System.out.println("Port >>>: " + this.port + " Nickname >>>: " + nickname);
            try {
                while (socketClient.isConnected()) {
                    System.out.println(nickname + " >>>: " + reader.readLine());
                }
            }
            catch (SocketException e ){}
            finally {
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
