package com.java_core.Lesson6.dz;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public final class Server {

    public static final String SOCKET_ADDR = "localhost";
    public static final int SOCKET_PORT = 8091;

    private ServerSocket sc;

    public static void main(String[] args) {
        new Server().runApplication();
    }

    private void runApplication() {
        try {
            sc = new ServerSocket(SOCKET_PORT);
            System.out.println("Сервер запущен, ожидает соединения");
            final Socket socket = sc.accept();
            System.out.println("Клиент подключился");

            final MyThreads myThreads = new MyThreads(socket);
            myThreads.runApplication();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                sc.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
