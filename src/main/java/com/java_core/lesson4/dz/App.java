package com.java_core.lesson4.dz;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class App extends JDialog {

    public static final int WIDTH_WINDOW = 500;
    public static final int HEIGHT_WINDOW = 400;

    private JPanel contentPane;
    private JButton buttonOK;
    private JTextField inputMessage;
    private JTextArea messageOutput;

    public App() {
        setContentPane(contentPane);
        setModal(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        getRootPane().setDefaultButton(buttonOK);

        // set size window and set location
        setPreferredSize(new Dimension(WIDTH_WINDOW, HEIGHT_WINDOW));
        final Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - WIDTH_WINDOW) / 2);
        int y = (int) ((dimension.getHeight() - HEIGHT_WINDOW) / 2);
        setLocation(x, y);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                final String newMessage = inputMessage.getText();
                if (!newMessage.equals("")) {
                    final String historyMessage = messageOutput.getText();
                    messageOutput.setText(historyMessage + newMessage + "\n");
                    inputMessage.setText("");
                }
            }
        });
    }

    public static void main(String[] args) {
        App dialog = new App();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
