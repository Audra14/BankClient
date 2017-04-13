/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bankclient;

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
    
    public void listenForInput(Socket socket){
        
    }
        
    public static void main(String[] args)throws Exception {
        
  
        BankClient client = new BankClient();
        Socket socket = new Socket("127.0.0.1", 6666);
        ClientHandler clientHandler = new ClientHandler(client, socket);
        clientHandler.start();

        while (true) {
            client.listenForInput(socket);
        } 
        
    }
    
    
    
}
