package com.java_core.lesson6.dz;

import java.io.*;
import java.net.Socket;

public final class Client {

    public static void main(String[] args) {
        new Client().runApplication();
    }

    private void runApplication() {
        try {
            final Socket socket = new Socket(com.java_core.lesson6.dz.Server.SOCKET_ADDR, com.java_core.lesson6.dz.Server.SOCKET_PORT);
            final com.java_core.lesson6.dz.MyThreads myThreads = new com.java_core.lesson6.dz.MyThreads(socket);
            myThreads.runApplication();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
