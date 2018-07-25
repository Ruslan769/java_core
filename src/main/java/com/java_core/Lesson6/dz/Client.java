package com.java_core.Lesson6.dz;

import java.io.*;
import java.net.Socket;

public final class Client {

    public static void main(String[] args) {
        new Client().runApplication();
    }

    private void runApplication() {
        try {
            final Socket socket = new Socket(Server.SOCKET_ADDR, Server.SOCKET_PORT);
            final MyThreads myThreads = new MyThreads(socket);
            myThreads.runApplication();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
