package com.java_core.Lesson6.dz;

import java.io.*;
import java.net.Socket;

public final class MyThreads {

    private Socket socket;

    private DataInputStream inputStream;
    private DataOutputStream outputStream;
    private BufferedReader bufferedReader;

    public MyThreads(Socket socket) {
        this.socket = socket;
    }

    public void runApplication() {
        try {
            inputStream = new DataInputStream(socket.getInputStream());
            outputStream = new DataOutputStream(socket.getOutputStream());
            bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            new Thread(new ThreadSockedIn()).start();
            new Thread(new ThreadSockedOut()).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private final class ThreadSockedIn implements Runnable {

        public void run() {
            while (true) {

                if (!socket.isConnected()) {
                    break;
                }

                try {
                    final String messageIn = inputStream.readUTF();
                    System.out.println("message: " + messageIn);
                } catch (IOException e) {
                    System.out.println("Кличент отключился");
                    break;
                }
            }
        }
    }

    private final class ThreadSockedOut implements Runnable {

        public void run() {
            while (true) {

                if (!socket.isConnected()) {
                    break;
                }

                try {
                    final String messageOut = bufferedReader.readLine();
                    outputStream.writeUTF(messageOut);
                    outputStream.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                    break;
                }
            }
        }
    }
}
