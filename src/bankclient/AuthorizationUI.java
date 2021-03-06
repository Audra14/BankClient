/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bankclient;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author nadaziab
 */
public class AuthorizationUI {

    private JFrame frame;
    private JPanel panel;
    private JLabel pinLabel;
    private JTextField pinField;
    private JButton submitBtn;
    private ClientHandler client;
    private boolean status;

    private DataOutputStream out;
    private DataInputStream in;

    public AuthorizationUI(ClientHandler client) {
        this.client = client;
        status = false;

        frame = new JFrame();
        frame.setSize(300, 300);
        frame.setLayout(new BorderLayout());
        frame.setTitle("Bank System");
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel = new JPanel();
        panel.setLayout(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;

        pinLabel = new JLabel("Enter 4-digit PIN#");
        c.gridx = 0;
        c.gridy = 0;
        panel.add(pinLabel, c);

        pinField = new JTextField();
        c.gridx = 0;
        c.gridy = 1;
        panel.add(pinField, c);

        submitBtn = new JButton("Submit");
        c.gridx = 0;
        c.gridy = 2;
        panel.add(submitBtn, c);

        frame.add(panel, BorderLayout.CENTER);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        addActionListeners();
    }

    private void write(String pin) {
        Socket socket = client.getSocket();

        try {
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());

            out.writeUTF(pin);

            String pinExists = in.readUTF();

            if (pinExists.equals("0")) {
                pinField.setText("");
                System.out.println("Incorrect PIN entered");
                pinExists = in.readUTF();
            } else {
                MenuUI menuUI = new MenuUI(client);
                frame.dispose();
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void addActionListeners() {

        submitBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent event) {
                String pin = pinField.getText();
                client.setPin(pin);
                write(pin);                
            }
        });
    }

}
