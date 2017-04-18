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
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author nadaziab
 */
public class WithdrawUI {

    private JFrame frame;
    private JPanel header, main;
    private JLabel acctNum, acctBal, withdrawAmt;
    private JTextField amtField;
    private JButton backBtn, withdrawBtn;
    private ClientHandler client;

    public WithdrawUI(ClientHandler client) {
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

        acctNum = new JLabel("** Acct Num here **");
        c.gridx = 0;
        c.gridy = 0;
        main.add(acctNum, c);

        acctBal = new JLabel("** Acct Bal here **");
        c.gridx = 0;
        c.gridy = 1;
        main.add(acctBal, c);

        withdrawAmt = new JLabel("Withdraw Amount:");
        c.gridx = 0;
        c.gridy = 2;
        main.add(withdrawAmt, c);

        amtField = new JTextField();
        c.gridx = 0;
        c.gridy = 3;
        main.add(amtField, c);

        withdrawBtn = new JButton("Confirm");
        c.gridx = 0;
        c.gridy = 4;
        main.add(withdrawBtn, c);

        header.add(backBtn, BorderLayout.WEST);
        frame.add(header, BorderLayout.NORTH);
        frame.add(main, BorderLayout.CENTER);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        addActionListeners();
    }
    
    private void addActionListeners() {
        
        backBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                MenuUI menuUI = new MenuUI(client);
                frame.dispose();
            }
        });
        withdrawBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
            // withdraw money
            }
        });
    }
}
