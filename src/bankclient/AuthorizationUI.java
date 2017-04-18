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
public class AuthorizationUI {
    
    private JFrame frame;
    private JPanel panel;
    private JLabel pinLabel;
    private JTextField pinField;
    private JButton submitBtn;
    private ClientHandler client;
    
    public AuthorizationUI(ClientHandler client) {
        this.client = client;
        
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
        
        submitBtn = new JButton();
        c.gridx = 0;
        c.gridy = 2;
        panel.add(submitBtn, c);
        
        frame.add(panel, BorderLayout.CENTER);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        
        addActionListeners();
    }

    private void addActionListeners() {
        submitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                String pin = pinField.getText();
                client.setPin(pin);
                MenuUI menuUI = new MenuUI(client);
                frame.dispose();
            }
        });
    }
}
