/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smtp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import model.Mail;

/**
 *
 * @author rosanne
 */
public class SmtpClient {
    
    Socket clientSocket;
    BufferedReader in;
    PrintWriter out;  
       
    public void disconnect()
    {
        try
        {
            in.close();
            out.close();
            clientSocket.close();
        }
        catch(IOException e)
        {
            System.out.println(e.getMessage());
        }
    }
    
    public void connection(String serverAddress, int serverPort)
    {
        try
        {
            String recept = "";
            clientSocket = new Socket(serverAddress, serverPort);                        
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            out = new PrintWriter(clientSocket.getOutputStream());
           
            recept = in.readLine();
            System.out.print(recept+"\n");
            
            if(!recept.startsWith("220 "))
            {
                disconnect();
                throw new Exception("Cannot open a socket");
            }
        }
        catch (Exception e) 
        {
            System.out.println(e.getMessage());
        }
        
    }
    
    public void sendMessage(Mail mail) throws Throwable
    {
        String recept;
        String messageSend;
        
        messageSend = "EHLO RES";
        System.out.println(messageSend);
        out.print(messageSend);
        out.print("\r\n");
        out.flush();

        do
        {
            recept = in.readLine();
            System.out.println(recept);
            if(!recept.startsWith("250"))
            {
                disconnect();
                throw new Throwable("Error EHLO");
            }

        }while(!recept.startsWith("250 "));

        messageSend = "MAIL FROM: " + mail.getFrom();
        System.out.println(messageSend);
        out.print(messageSend);        
        out.print("\r\n");
        out.flush();

         recept = in.readLine();
         System.out.println(recept);

         if(recept.startsWith("250 "))
         {                  
             for(String to : mail.getTo())
             {
                 messageSend = "RCPT TO: " + to;
                 System.out.println(messageSend);
                 out.print(messageSend);
                 out.print("\r\n");
                 out.flush();

                 recept = in.readLine();
                 System.out.println(recept);
                 if(!recept.startsWith("250 "))
                 {
                     disconnect();
                     throw new Throwable("Error RCPT");

                 }

             }
             
             messageSend = "DATA";
             System.out.println(messageSend);
             out.print(messageSend);             
             out.print("\r\n");
             out.flush();
             
             recept = in.readLine();
             System.out.println(recept);
             if(!recept.startsWith("354 "))
             {
                 disconnect();
                 throw new Throwable("Error DATA");                     
             }
             else
             {
                 // Header
                 out.print("From: ");
                 out.print(mail.getFrom());
                 out.print("\r\n");
                 
                 out.print("To: ");
                 for(String to : mail.getTo())
                 {
                     out.print(to);
                     out.print(", ");
                 }
                 out.print("\r\n");
                 
                 out.print(mail.getSubject());
                 out.print("\r\n");
                 out.print("\r\n");
                 
                 out.print(mail.getMessage());
                 
                 // End of message
                 out.print("\r\n");
                 out.print(".");
                 out.print("\r\n");
                 out.flush();
                 recept = in.readLine();
                 System.out.println(recept);
                 if(!recept.startsWith("250 "))
                 {
                     disconnect();
                     throw new Throwable("Error body message");
                 }
             }

         }
         else
         {
             disconnect();
             throw new Throwable("Error MAIL FROM");
         }
            
    }
    
}
