/* *************************************************************************
 * HEIG-VD, Cours RES
 *
 * File        : ConfigReader.java
 * Authors     : Combremont Rosanne & Ponce Kevin
 * Created on  : 20.04.2016
 *
 * Description : This class reads the contents of the config files in the 
 *               config folder and make it available for the other classes
 *
 *
 **************************************************************************/
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


public class ConfigReader {
    private List<String> adresses = new ArrayList<>();
    private List<String> msg = new ArrayList<>();
    private String serverAddr;
    private int port;
    private int nbGroups;
    
    public List<String> getAdresses()
    {
        return new ArrayList<>(adresses);
    }
    // Read the email addresses from the file and put it into a list
    public void setAdresses()
    {
        // Read in the config/victims.utf8 file
        try
        {
            BufferedReader adrReader = new BufferedReader(new InputStreamReader(new FileInputStream("config/victims.utf8")));
        
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
    // Read the messages from the file and put it into a list. The character
    // "==" is used as a separator between two messages
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
    
    // Method that reads the .properties file
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
