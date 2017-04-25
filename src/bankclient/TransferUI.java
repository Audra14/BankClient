/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bankclient;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.net.*;
import java.io.*;
import javax.swing.JOptionPane;

/**
 *
 * @author nadaziab
 */
public class TransferUI {
    private JFrame frame;
    private JPanel header, main;
    private JLabel acctNum, acctBal, transferAcct, transferAmt;
    private JTextField transAcctField, amtField;
    private JButton backBtn, transferBtn;
    private ClientHandler client;
    
    private DataOutputStream out;
    private DataInputStream in;
    
    public TransferUI(ClientHandler client) {
        this.client = client;
        
        frame = new JFrame();
        frame.setSize(300, 300);
        frame.setLayout(new BorderLayout());
        frame.setTitle("Bank System");
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        header = new JPanel(new BorderLayout());
        main = new JPanel(new GridBagLayout());
        
        backBtn = new JButton("Back");
        
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        

        transferAcct = new JLabel("Enter 4-digit PIN# for account to transfer to");
        c.gridx = 0;
        c.gridy = 0;
        main.add(transferAcct, c);
        
        transAcctField = new JTextField();
        c.gridx = 0;
        c.gridy = 1;
        main.add(transAcctField, c);
        
        transferAmt = new JLabel("Transfer Amount:");
        c.gridx = 0;
        c.gridy = 2;
        main.add(transferAmt, c);

        amtField = new JTextField();
        c.gridx = 0;
        c.gridy = 3;
        main.add(amtField, c);

        transferBtn = new JButton("Confirm");
        c.gridx = 0;
        c.gridy = 4;
        main.add(transferBtn, c);

        header.add(backBtn, BorderLayout.WEST);
        frame.add(header, BorderLayout.NORTH);
        frame.add(main, BorderLayout.CENTER);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        addActionListeners();
    }
    
    private void write (String acct, double amount){
        Socket socket = client.getSocket();
        
        String pin = client.getPin();
        String specifier = client.getSpecifier();
        
        try {
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
            
            out.writeUTF(pin + " " + specifier + " " + acct + " " + amount);
            
            String feedback = in.readUTF();
            Component frame = new JFrame();
            JOptionPane.showMessageDialog(frame, feedback);
            
            out.writeUTF(pin);
            in.readUTF();
            
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    private void addActionListeners() {
        backBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                MenuUI menuUI = new MenuUI(client);
                frame.dispose();
            }
        });
        transferBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                String acctTo = transAcctField.getText();
                double transferAmt = Double.valueOf(amtField.getText());
                write(acctTo, transferAmt);
                MenuUI menuUI = new MenuUI(client); //back to main menu
                frame.dispose();
            }
        });
    }
}
