package com.company;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.*;
import java.util.Random;
public class Main {
    public static void main(String[] args) throws IOException {
        int mainPort = 0;
        ServerSocket socket = new ServerSocket(6745);
        try {
            Socket socketClient = socket.accept();
            int min = 2000;
            int max = 6000;
            int diff = max - min;
            Random random = new Random();
            mainPort = random.nextInt(diff + 1);
            mainPort += min;
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socketClient.getOutputStream()));
            while (true) {
                writer.write(mainPort);
                writer.flush();
            }
        }
        catch (SocketException e){}
        finally {
            socket.close();
            ClientHandler handler = new ClientHandler(mainPort);
            handler.start();
            main(args);
        }
    }
}
