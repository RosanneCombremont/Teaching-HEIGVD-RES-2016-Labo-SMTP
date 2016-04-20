/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;
import config.ConfigReader;
import java.util.List;
import java.util.Random;
import java.util.ArrayList;
import smtp.SmtpClient;

/**
 *
 * @author rosanne
 */
public class JokeGenerator {
    private int nbGroup;
    private ConfigReader config;
    private List<Group> groups;
    private SmtpClient smtpClient;
    
    public JokeGenerator(ConfigReader config, SmtpClient smtp) throws Exception
    {
        this.config = config;
        this.nbGroup = config.getNbGroups();
        this.groups = makeGroup();
        this.smtpClient = smtp;
    }
    
    //faire x groupes à partir des adresses
    private List<Group> makeGroup() throws Exception
    {
        List<String> adresses = config.getAdresses();
        List<Group> allGroups = new ArrayList<>();
        List<Integer> check = new ArrayList<>();
        Random rand = new Random();
                
        //Si pas assez d'adresse pour faire des groupes hétéogènes
        if((adresses.size() / nbGroup) < 3)
        {
            throw new Exception("Not enough mail addresses");
            
        }
        else
        {
            
            int nbVictimPerGroup = adresses.size() / nbGroup;
            int z;
            for(int i = 0; i < nbGroup; i++)
            {
                Group group = new Group();
                for(int m = 0; m < nbVictimPerGroup; m++)
                {
                    z = rand.nextInt(adresses.size());
                    while(check.contains(z))
                    {
                        z = rand.nextInt(adresses.size());
                    }
                    check.add(z);
                    group.addVictim(new Person(adresses.get(z)));
                    
                }
                check.clear();
                allGroups.add(group);
            }
        }
        
        return allGroups;
    }
    
    //selectionner le msg
    public String selectMsg()
    {
        List<String> msg = config.getMsg();
        Random random = new Random();
        
        return msg.get(random.nextInt(msg.size()));
    }
    
    //créer une joke par groupe
    public List<Joke> createJokes()
    {
        List<Joke> jokes = new ArrayList<>();
        List<Person> members;
        Random r = new Random();
        int randSender;
        for(Group g : groups)
        {
            Joke j = new Joke();
            // Define the sender
            randSender = r.nextInt(g.getVictims().size());
            j.setVictimSender(g.getVictims().get(randSender));
            // Set the other members
            members = g.getVictims();
            members.remove(randSender);
            j.addReceivers(members);
            //Set the text message
            j.setMessage(selectMsg());
                        
            jokes.add(j);
        }
        return jokes;
    }
    
    //envoyer le mail
    public void sendJokes()
    {
        List<Joke> jokes = createJokes();
        Mail mail;
        for(Joke j : jokes)
        {
            mail = j.createEmail();
            try
            {
                smtpClient.sendMessage(mail);
            }
            catch(Throwable e)
            {
                System.out.println("Error send joke");
            }
            
        }
    }  
    
    
    
    
    
}
