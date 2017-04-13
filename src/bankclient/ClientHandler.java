/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bankclient;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author astafursky
 */
public class ClientHandler extends Thread {
    
    DataInputStream in;
    DataOutputStream out;
    BufferedReader input;

    BankClient bankClient;
    Socket socket;

    public ClientHandler(BankClient bankClient, Socket socket) {
        this.bankClient = bankClient;
        this.socket = socket;

    }

    public void run() {

        //this should be seeking input from GUI
        try {
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
