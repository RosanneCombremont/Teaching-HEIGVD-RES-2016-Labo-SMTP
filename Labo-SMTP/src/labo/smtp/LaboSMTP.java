/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package labo.smtp;

import config.ConfigReader;
import model.JokeGenerator;
import java.util.Scanner;
import smtp.SmtpClient;

/**
 *
 * @author rosanne
 */
public class LaboSMTP {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Read the config files
        ConfigReader conf = new ConfigReader();
        conf.setAdresses();
        conf.setMsg();
        conf.readProperties();
        
        // Set a smtp client
        SmtpClient smtpClient = new SmtpClient();
        smtpClient.connection(conf.getServerAddr(), conf.getPort());
             
        
        
        // invoke JokeGenerator
        try
        {
            JokeGenerator joker = new JokeGenerator(conf, smtpClient);
            joker.sendJokes();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        
    }
    
}
