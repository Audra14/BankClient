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
import java.util.Scanner;
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
     
            out = new DataOutputStream(socket.getOutputStream());
            in = new DataInputStream(socket.getInputStream());
            
            Scanner input = new Scanner(System.in);
            System.out.println("Enter 4-digit PIN#");
            
            String message = input.next();
            String specifier;
            String amount= "";
            
            while(message.length() != 4){
                System.out.println("Invalid PIN, must be exactly 4 digits.");
                System.out.println("Enter 4-digit PIN#");
                message = input.next();   
            }
            
            System.out.println("Enter 0 for Balance Inquiry \n Enter 1 for Deposit \n Enter 2 for Withdrawal \n Enter 3 for Transfer");
            specifier = input.next();

            if(!specifier.equals("0")){
                System.out.println("Enter amount for transaction: ");
                amount = input.next();
            }
            
            
            out.writeUTF(message + " " + specifier + " " + amount);
           
            System.out.println(in.readUTF());
            
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
