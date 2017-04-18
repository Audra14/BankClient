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
    //BufferedReader input;

    BankClient bankClient;
    Socket socket;

    String specifier;
    String acctNum;
    String pin = "0000";
    
    Double transactionAmount;
    String transferAccount;
    
    boolean authorized;

    public ClientHandler(BankClient bankClient, Socket socket) {
        this.bankClient = bankClient;
        this.socket = socket;
        authorized = false;

        AuthorizationUI menu = new AuthorizationUI(this);
    }

    
    public void setSpecifier(String specifier) {
        this.specifier = specifier;
    }

    public void setAcctNum(String acctNum) {
        this.acctNum = acctNum;

    }
    
    public void setPin (String pin){
        this.pin = pin;
        System.out.println("Pin is set.");
    }
    
    public void setAmount(double amount){
        this.transactionAmount = amount;
    }
    public void setAuthorized(boolean status) {
        authorized = true;
    }

    public void run() {

        //this should be seeking input from GUI
        try {
    
            out = new DataOutputStream(socket.getOutputStream());
            in = new DataInputStream(socket.getInputStream());

            Scanner input = new Scanner(System.in);

          
            System.out.println("Validating PIN# " + this.pin + "...");
            out.writeUTF(this.pin);

            String pinExists = in.readUTF();

            while(pinExists.equals("0")){
                System.out.println("PIN does not exist, re-enter PIN#");
                out.writeUTF(input.next());
                pinExists = in.readUTF();
            }
                
            
            
            System.out.println("PIN# Confirmed.");

            System.out.println("Enter 0 for Balance Inquiry \n Enter 1 for Deposit \n Enter 2 for Withdrawal \n Enter 3 for Transfer");
            //specifier = input.next();
            System.out.println("Specifier = " + specifier);
            
            if (!specifier.equals("0")) {

                if (specifier.equals("3")) {

                    out.writeUTF(this.pin + " " + specifier + " " + transferAccount + " " + transactionAmount);

                } else {
                    out.writeUTF(this.pin + " " + specifier + " " + transactionAmount);
                }

            } else {
                out.writeUTF(this.pin + " " + specifier);
            }

            System.out.println(in.readUTF());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
