/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package config;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 *
 * @author rosanne
 */
public class ConfigReader {
    private List<String> adresses = new ArrayList<>();
    private List<String> msg = new ArrayList<>();
    private String serverAddr;
    private int port;
    private int nbGroups;
    // config server
    
    public List<String> getAdresses()
    {
        return new ArrayList<>(adresses);
    }
    public void setAdresses()
    {
        //lecture dans le fichier victims.utf8
        try
        {
            BufferedReader adrReader = new BufferedReader(new InputStreamReader(new FileInputStream("config/victims.utf8")));
        
            int i = 0;
            String tmp = adrReader.readLine();
            while(tmp != null)
            {
                adresses.add(tmp);
                tmp = adrReader.readLine();
            }
        
            adrReader.close();
        }
        catch(IOException e){}
    }
    
    public List<String> getMsg()
    {
        return new ArrayList<>(msg);
    }
    public void setMsg()
    {
        try
        {
            BufferedReader msgReader = new BufferedReader(new InputStreamReader(new FileInputStream("config/message.utf8")));
            
            String message = "";
            String tmp = msgReader.readLine();
            while(tmp != null)
            { 
                if(tmp.equals("=="))
                {
                    msg.add(message);
                    message = "";
                }
                else
                {
                    message += tmp + "\n";
                }
                tmp = msgReader.readLine();
            }                    
            msgReader.close();
        }
        catch(IOException e){}
    }
    
    public int getNbGroups()
    {
        return nbGroups;
    }
    
    public int getPort()
    {
        return port;
    }
    
    public String getServerAddr()
    {
        return serverAddr;
    }
    
    public void readProperties()
    {
        Properties properties = new Properties();
        try
        {
            properties.load(new FileInputStream("config/config.properties"));
            this.serverAddr = properties.getProperty("smtpServerAddress");
            this.port = Integer.parseInt(properties.getProperty("smtpServerPort"));
            this.nbGroups = Integer.parseInt(properties.getProperty("numberOfGroups"));
        }
        catch(IOException e)
        {
            System.out.println("Cannot read properties file");
        }
    }
    
}
