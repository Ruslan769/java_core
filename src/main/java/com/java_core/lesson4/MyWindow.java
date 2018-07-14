package com.java_core.lesson4;

import javax.swing.*;

public class MyWindow extends JFrame {

    public MyWindow() {
        setTitle("Test window");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(300, 300, 400, 400);
    }

    public static void main(String[] args) {
        final MyWindow window = new MyWindow();
        window.setVisible(true);
    }
}
