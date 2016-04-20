/* *************************************************************************
 * HEIG-VD, Cours RES
 *
 * File        : LaboSMTP.java
 * Authors     : Combremont Rosanne & Ponce Kevin
 * Created on  : 20.04.2016
 *
 * Description : Application that generate forged emails automatically.
 *
 *
 **************************************************************************/
package labo.smtp;

import config.ConfigReader;
import model.JokeGenerator;
import smtp.SmtpClient;


public class LaboSMTP {


    public static void main(String[] args) {
        
        // Read the config files
        ConfigReader conf = new ConfigReader();
        conf.setAdresses();
        conf.setMsg();
        conf.readProperties();
        
        // Set a smtp client
        SmtpClient smtpClient = new SmtpClient();
        smtpClient.connection(conf.getServerAddr(), conf.getPort());
                     
        // invoke the JokeGenerator
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
