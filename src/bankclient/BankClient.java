/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bankclient;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 *
 * @author astafursky
 */
public class BankClient {

    Socket socket;
    /**
     * @param args the command line arguments
     */
    
    public BankClient (){
        try {
            socket = new Socket("127.0.0.1", 6666);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public String getServerAddress(){
        return "127.0.0.1";
    }
    
    public static void main(String[] args)throws Exception {
        
  
        BankClient client = new BankClient();
        Socket socket = new Socket("127.0.0.1", 6666);
        ClientHandler clientHandler = new ClientHandler(client, socket);
        
        while (clientHandler.authorized == false) {
            System.out.println("false");
            // do nothing
        }
        clientHandler.start();
        
    }
    
    
    
}
