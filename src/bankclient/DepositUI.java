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
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author nadaziab
 */
public class DepositUI {
    
    private JFrame frame;
    private JPanel header, main;
    private JLabel acctNum, acctBal, depositAmt;
    private JTextField amtField;
    private JButton backBtn, depositBtn;
    private ClientHandler client;
    
    private DataOutputStream out;
    private DataInputStream in;
    
    public DepositUI(ClientHandler client) {
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

        
        depositAmt = new JLabel("Deposit Amount:");
        c.gridx = 0;
        c.gridy = 0;
        main.add(depositAmt, c);
        
        amtField = new JTextField();
        c.gridx = 0;
        c.gridy = 1;
        main.add(amtField, c);
        
        depositBtn = new JButton("Confirm");
        c.gridx = 0;
        c.gridy = 2;
        main.add(depositBtn, c);
        
        header.add(backBtn, BorderLayout.WEST);
        frame.add(header, BorderLayout.NORTH);
        frame.add(main, BorderLayout.CENTER);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        addActionListeners();
    }
    
    private void write (double amount){
       
        Socket socket = client.getSocket();

        String pin = client.getPin();
        String specifier = client.getSpecifier();

        try {
            out = new DataOutputStream(socket.getOutputStream());
            in = new DataInputStream(socket.getInputStream());
            out.writeUTF(pin + " " + specifier + " " + amount);
            
            
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
        depositBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                double depositAmt = Double.valueOf(amtField.getText());
                
                write(depositAmt);
           
                MenuUI menuUI = new MenuUI(client); //back to main menu
                frame.dispose();
            }
        });
    }
}
