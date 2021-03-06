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

/**
 *
 * @author nadaziab
 */
public class BalanceInquiryUI {
    
    private JFrame frame;
    private JPanel header, main;
    private JLabel acctNum, acctBal;
    private JButton backBtn;
    private ClientHandler client;
    
    private DataInputStream in;
    private DataOutputStream out;
    
    public BalanceInquiryUI(ClientHandler client) {
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

        acctBal = new JLabel("** Acct Bal here **");
        c.gridx = 0;
        c.gridy = 0;
        main.add(acctBal, c);
        
        header.add(backBtn, BorderLayout.WEST);
        frame.add(header, BorderLayout.NORTH);
        frame.add(main, BorderLayout.CENTER);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        display();
        addActionListeners();
    }
    
    private void display(){
        Socket socket = client.getSocket();
        
        String pin = client.getPin();
        String specifier = client.getSpecifier();
        
        try{
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
            
            out.writeUTF(pin + " " + specifier);
            acctBal.setText(in.readUTF());
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
    }
}
