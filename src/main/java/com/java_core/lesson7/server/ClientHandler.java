package com.java_core.lesson7.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public final class ClientHandler {

    private ServiceApplication mServer;
    private Socket mSocket;
    private DataInputStream in;
    private DataOutputStream out;
    private String name;

    public String getName() {
        return name;
    }

    public ClientHandler(ServiceApplication server, Socket socket) {
        try {
            this.mServer = server;
            this.mSocket = socket;
            this.in = new DataInputStream(mSocket.getInputStream());
            this.out = new DataOutputStream(mSocket.getOutputStream());
            this.name = "";
            new Thread(() -> {
                try {
                    while (true) { // цикл авторизации
                        final String str = in.readUTF();
                        if (str.startsWith(ServiceApplication.COMMAND_AUTH)) {
                            final String[] parts = str.split("\\s");
                            final String nick = mServer.getAuthService().getNickByLoginPass(parts[1], parts[2]);
                            if (nick != null) {
                                if (!mServer.isNickBusy(nick)) {
                                    sendMsg(ServiceApplication.COMMAND_AUTHOK + " " + nick);
                                    name = nick;
                                    mServer.broadcastMsg(name + " зашел в чат");
                                    mServer.subscribe(this);
                                    break;
                                } else sendMsg("Учетная запись уже используется");
                            } else {
                                sendMsg("Неверные логин/пароль");
                            }
                        }
                    }
                    while (true) { // цикл получения сообщений
                        final String str = in.readUTF();
                        System.out.println("от " + name + ": " + str);
                        if (str.equals(ServiceApplication.COMMAND_END)) break;
                        mServer.broadcastMsg(name, str);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    mServer.unsubscribe(this);
                    mServer.broadcastMsg(name + " вышел из чата");
                    try {
                        mSocket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        } catch (IOException e) {
            throw new RuntimeException("Проблемы при создании обработчика клиента");
        }
    }

    public void sendMsg(String msg) {
        try {
            out.writeUTF(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
