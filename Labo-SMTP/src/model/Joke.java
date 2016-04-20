/* *************************************************************************
 * HEIG-VD, Cours RES
 *
 * File        : Joke.java
 * Authors     : Combremont Rosanne & Ponce Kevin
 * Created on  : 20.04.2016
 *
 * Description : This class contains what's necessary to make a joke:
 *               - a victim sender
 *               - a list of victim receivers
 *               - and the message (subject + body)
 *
 *
 **************************************************************************/
package model;

import java.util.ArrayList;
import java.util.List;


public class Joke {
    private Person victimSender;
    private final List<Person> victimReceivers = new ArrayList<>();
    private String message;
    
    public Person getVictimSender()
    {
        return victimSender;
    }
    public void setVictimSender(Person sender)
    {
        this.victimSender = sender;
    }
    
    public void setMessage(String msg)
    {
        this.message = msg;
    }
    public String getMessage()
    {
        return message;
    }
    
    public void addReceivers(List<Person> victims)
    {
        victimReceivers.addAll(victims);
    }
    public void addReceiver(Person victim)
    {
        victimReceivers.add(victim);
    }
    
    public List<Person> getReceivers()
    {
        return new ArrayList<>(victimReceivers);
    }
    
    // Create the email with the given info
    public Mail createEmail()
    {
        Mail mail = new Mail();
        
        // Set the email sender
        mail.setFrom(victimSender.getMailAddress());
        
        // Seperate the Subject and the Body of the message
        int index = message.indexOf(';');
        String subj = message.substring(0, index);
        String body = message.substring(index+1, message.length());
        mail.setSubject(subj);
        
        // Add a signature
        String tmp = body + "\n\r" + victimSender.getFirstName();
        mail.setMessage(tmp);
        
        // Set all the receivers
        String[] victims = new String[victimReceivers.size()];
        for(int i = 0; i < victimReceivers.size(); i++)
        {
            victims[i] = victimReceivers.get(i).getMailAddress();
        }
        mail.setTo(victims);
        
        return mail;        
    }    
}
