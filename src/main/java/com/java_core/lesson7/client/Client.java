package com.java_core.lesson7.client;

import com.java_core.lesson7.server.ServiceApplication;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public final class Client extends JFrame {

    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;

    private JTextField jtf;
    private JTextArea textArea;

    public static void main(String[] args) {
        new Client();
    }

    public Client() {
        try {
            socket = new Socket(ServiceApplication.SERVER_ADDR, ServiceApplication.SERVER_PORT);
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
            setAuthorized(false);
            Thread t = new Thread(() -> {
                try {
                    while (true) {
                        final String str = in.readUTF();
                        if (str.startsWith(ServiceApplication.COMMAND_AUTHOK)) {
                            setAuthorized(true);
                            break;
                        }
                        textArea.append(str + "\n");
                    }
                    while (true) {
                        final String str = in.readUTF();
                        if (str.equals(ServiceApplication.COMMAND_END)) {
                            break;
                        }
                        textArea.append(str + "\n");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        socket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    setAuthorized(false);
                }
            });
            t.setDaemon(true);
            t.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
        setBounds(600, 300, 500, 500);
        setTitle("Client");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        final JScrollPane jsp = new JScrollPane(textArea);
        add(jsp, BorderLayout.CENTER);
        final JPanel bottomPanel = new JPanel(new BorderLayout());
        add(bottomPanel, BorderLayout.SOUTH);
        JButton jbAuth = new JButton("AUTH");
        JButton jbSend = new JButton("SEND");
        bottomPanel.add(jbSend, BorderLayout.EAST);
        bottomPanel.add(jbAuth, BorderLayout.WEST);
        jtf = new JTextField();
        bottomPanel.add(jtf, BorderLayout.CENTER);
        jbSend.addActionListener(e -> {
            if (!jtf.getText().trim().isEmpty()) {
                sendMsg();
                jtf.grabFocus();
            }
        });
        jbAuth.addActionListener(e -> onAuthClick());
        jtf.addActionListener(e -> sendMsg());
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                try {
                    out.writeUTF("end");
                    out.flush();
                    socket.close();
                    out.close();
                    in.close();
                } catch (IOException exc) {
                }
            }
        });
        setVisible(true);
    }

    public void setAuthorized(boolean v) {

    }

    public void onAuthClick() {
        try {
            final String login = "login1";
            final String password = "pass1";
            out.writeUTF(ServiceApplication.COMMAND_AUTH + " " + login + " " + password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendMsg() {
        try {
            out.writeUTF(jtf.getText());
            jtf.setText("");
        } catch (IOException e) {
            System.out.println("Ошибка отправки сообщения");
        }
    }

}
