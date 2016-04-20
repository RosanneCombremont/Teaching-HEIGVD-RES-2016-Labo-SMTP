/* *************************************************************************
 * HEIG-VD, Cours RES
 *
 * File        : SmtpClient.java
 * Authors     : Combremont Rosanne & Ponce Kevin
 * Created on  : 20.04.2016
 *
 * Description : Implementation of a smtp client.
 *
 *
 **************************************************************************/
package smtp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import model.Mail;


public class SmtpClient {
    
    private Socket clientSocket;
    private BufferedReader in;
    private PrintWriter out;  
     
    // Disconnect from the socket, reader and writer
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
            // Creation of the socket
            clientSocket = new Socket(serverAddress, serverPort);    
            // Initialisation of the reader and writer
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
    
    // Send an email using parts of STMP protocole
    public void sendMessage(Mail mail) throws Throwable
    {
        String recept;
        String messageSend;
        
        // EHLO command
        messageSend = "EHLO RES";
        System.out.println(messageSend);
        out.print(messageSend);
        out.print("\r\n");
        out.flush();

        // Read the response 
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

        // MAIL FROM:
        messageSend = "MAIL FROM: " + mail.getFrom();
        System.out.println(messageSend);
        out.print(messageSend);        
        out.print("\r\n");
        out.flush();

         recept = in.readLine();
         System.out.println(recept);

         if(recept.startsWith("250 "))
         {        
             // RCPT TO:
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
             
             // DATA
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
                 // Email header
                 // From:
                 out.print("From: ");
                 out.print(mail.getFrom());
                 out.print("\r\n");
                 
                 // To:
                 out.print("To: ");
                 for(String to : mail.getTo())
                 {
                     out.print(to);
                     out.print(", ");
                 }
                 out.print("\r\n");
                 
                 // Subject:
                 out.print(mail.getSubject());
                 out.print("\r\n");
                 out.print("\r\n");
                 
                 // Email body
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
