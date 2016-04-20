/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rosanne
 */
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
    
    public Mail createEmail()
    {
        Mail mail = new Mail();
        
        mail.setFrom(victimSender.getMailAddress());
        
        System.out.print(message);
        int index = message.indexOf(';');
        String subj = message.substring(0, index);
        String body = message.substring(index+1, message.length());
        mail.setSubject(subj);
        
        String tmp = body + "\n\r" + victimSender.getFirstName();
        mail.setMessage(tmp);
        String[] victims = new String[victimReceivers.size()];
        for(int i = 0; i < victimReceivers.size(); i++)
        {
            victims[i] = victimReceivers.get(i).getMailAddress();
        }
        mail.setTo(victims);
        
        return mail;        
    }    
}
