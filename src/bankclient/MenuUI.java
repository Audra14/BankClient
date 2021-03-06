/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bankclient;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 *
 * @author nadaziab
 */
public class MenuUI {

    private JFrame frame;
    private JPanel panel, header;
    private JButton balInquiry, deposit, transfer, withdraw, submit;
    private JLabel acctNum, acctBal;
    private JButton backBtn;
    public ClientHandler client;
    
    private DataOutputStream out;
    private DataInputStream in;
    

    public MenuUI(ClientHandler client) {
        
        frame = new JFrame();
        frame.setSize(300, 300);
        frame.setLayout(new BorderLayout());
        frame.setTitle("Bank System");
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.client = client;
        panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        
        header = new JPanel();
        header.setLayout(new BorderLayout());
        backBtn = new JButton("Back");

        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        
       
        createButtons();
        addActionListeners();

        frame.add(panel, BorderLayout.CENTER);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public void createButtons() {
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        
        balInquiry = new JButton("Balance Inquiry");
        c.gridx = 0;
        c.gridy = 0;
        panel.add(balInquiry, c);
        
        deposit = new JButton("Deposit");
        c.gridx = 0;
        c.gridy = 1;
        panel.add(deposit, c);
        
        transfer = new JButton("Transfer");
        c.gridx = 0;
        c.gridy = 2;
        panel.add(transfer, c);
        
        withdraw = new JButton("Withdraw");
        c.gridx = 0;
        c.gridy = 3;
        panel.add(withdraw, c);

    }
    
    public void write(String specifier){
        
        Socket socket = client.getSocket();
        String pin = client.getPin();
        
        try {
            if (!specifier.equals("0")) {

                if (specifier.equals("3")) {

                    //out.writeUTF(pin + " " + specifier + " " + transferAccount + " " + transactionAmount);

                } else {
                    //out.writeUTF(pin + " " + specifier + " " + transactionAmount);
                }

            } else {
                out.writeUTF(pin + " " + specifier);
            }

            System.out.println(in.readUTF());
        } catch (IOException e){
            e.printStackTrace();
        }
        
    }
    

    public void addActionListeners() {
        
        balInquiry.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                client.setSpecifier("0");
                BalanceInquiryUI balanceInquiryUI = new BalanceInquiryUI(client);
                frame.dispose();
            }
        });
        
        deposit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                client.setSpecifier("1");
                DepositUI depositUI = new DepositUI(client);
                frame.dispose();
            }
        });
        withdraw.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                client.setSpecifier("2");
                WithdrawUI withdrawUI = new WithdrawUI(client);
                frame.dispose();
            }
        });
        transfer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                client.setSpecifier("3");
                TransferUI transferUI = new TransferUI(client);
                frame.dispose();
            }
        }); 
    }
}
