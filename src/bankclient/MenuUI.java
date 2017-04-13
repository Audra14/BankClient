/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bankclient;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author nadaziab
 */
public class MenuUI extends JFrame {

    private JPanel panel;
    private JButton balInquiry, deposit, transfer, withdraw;
    private JLabel acctNum, acctBal;

    public MenuUI() {
        
        this.setSize(300, 300);
        this.setTitle("Bank System");
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel = new JPanel();
        //panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

        acctNum = new JLabel("** Account Number here **");

        panel.add(acctNum);
        panel.add(Box.createRigidArea(new Dimension(0, 5)));

        createButtons();
        addActionListeners();

        this.add(panel);
        this.setVisible(true);
    }

    public void createButtons() {
        balInquiry = new JButton("Balance Inquiry");
        deposit = new JButton("Deposit");
        transfer = new JButton("Transfer");
        withdraw = new JButton("Withdraw");

        panel.add(balInquiry);
        panel.add(Box.createRigidArea(new Dimension(0, 5)));
        panel.add(deposit);
        panel.add(Box.createRigidArea(new Dimension(0, 5)));
        panel.add(transfer);
        panel.add(Box.createRigidArea(new Dimension(0, 5)));
        panel.add(withdraw);
    }

    public void addActionListeners() {

        balInquiry.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
            // get balance
            // display balance
            }
        });
        
        deposit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                displayDepositScreen();
            }
        });
        
        transfer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
            // new screen - transfer
            }
        });
        
        withdraw.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
            // new screen - withdraw
            }
        });
    }
    
    public void displayDepositScreen() {

        panel.revalidate();
        panel.removeAll();
        panel = new JPanel();
        //panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        
        
        JLabel depositAmt = new JLabel("Deposit Amount:");
        JTextField amtField = new JTextField(10);
        JButton depositBtn = new JButton("Confirm");
        
        acctBal = new JLabel("** Bal here **");
        
        panel.add(acctNum);
        panel.add(acctBal);
        panel.add(depositAmt);
        panel.add(Box.createRigidArea(new Dimension(0, 5)));
        panel.add(amtField);
        panel.add(Box.createRigidArea(new Dimension(0, 5)));
        panel.add(depositBtn);
        
        this.add(panel);
        
        
        // ** add action listener **
    }
    
    public void displayTransferScreen() {
        
    }
    
    public void displayWithdrawScreen() {
        
    }
}
