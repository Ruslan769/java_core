package com.java_core.lesson8.server;

import com.java_core.lesson7.server.service.AuthService;
import com.java_core.lesson7.server.service.BaseAuthService;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public final class ServiceApplication {

    public static final String SERVER_ADDR = "localhost";
    public static final int SERVER_PORT = 8090;
    public static final String COMMAND_END = "/end";
    public static final String COMMAND_AUTH = "/auth";
    public static final String COMMAND_AUTHOK = "/authok";
    public static final String COMMAND_POINTER = "/w";

    private ServerSocket server;
    private Socket socket;
    private List<ClientHandler> clients;
    private AuthService authService;

    public AuthService getAuthService() {
        return authService;
    }

    public static void main(String[] args) {
        final ServiceApplication mSA = new ServiceApplication();
        mSA.runApplication();
    }

    private void runApplication() {
        try {
            server = new ServerSocket(SERVER_PORT);
            authService = new BaseAuthService();
            authService.start();
            clients = new ArrayList<>();
            while (true) {
                System.out.println("Сервер ожидает подключения");
                socket = server.accept();
                System.out.println("Клиент подключился");
                new ClientHandler(this, socket);
            }
        } catch (IOException e) {
            System.out.println("Ошибка при работе сервера");
        } finally {
            try {
                server.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            authService.stop();
        }
    }

    public synchronized boolean isNickBusy(String nick) {
        for (ClientHandler o : clients) {
            if (o.getName().equals(nick)) return true;
        }
        return false;
    }

    public synchronized void broadcastMsg(String msg) {
        for (ClientHandler o : clients) {
            o.sendMsg(msg);
        }
    }

    public synchronized void broadcastMsg(final String fromNick, final String msg) {
        final String[] arPiecesMsg = msg.split("\\s");
        String toNick = "";
        int pointerPosition = -1;
        for (int i = 0; i < arPiecesMsg.length; i++) {
            final String piecesMsg = arPiecesMsg[i];
            if (pointerPosition != -1) {
                toNick = piecesMsg;
                break;
            }
            if (piecesMsg.equals(ServiceApplication.COMMAND_POINTER)) {
                pointerPosition = i;
            }
        }

        for (ClientHandler o : clients) {
            if (toNick.isEmpty()) {
                o.sendMsg(fromNick + ": " + msg);
            } else if (fromNick.equals(o.getName()) || toNick.equals(o.getName())) {
                final int dotPosition = msg.lastIndexOf(toNick) + toNick.length() + 1;
                final String msgFilter = fromNick + ": " + msg.substring(dotPosition);
                o.sendMsg(msgFilter);
            }
        }
    }

    public synchronized void unsubscribe(ClientHandler o) {
        clients.remove(o);
    }

    public synchronized void subscribe(ClientHandler o) {
        clients.add(o);
    }

}
