/* *************************************************************************
 * HEIG-VD, Cours RES
 *
 * File        : JokeGenerator.java
 * Authors     : Combremont Rosanne & Ponce Kevin
 * Created on  : 20.04.2016
 *
 * Description : Business implementation.
 *
 *
 **************************************************************************/
package model;
import config.ConfigReader;
import java.util.List;
import java.util.Random;
import java.util.ArrayList;
import smtp.SmtpClient;


public class JokeGenerator {
    private int nbGroup;
    private ConfigReader config;
    private List<Group> groups;
    private SmtpClient smtpClient;
    
    // Constructor
    public JokeGenerator(ConfigReader config, SmtpClient smtp) throws Exception
    {
        this.config = config;
        this.nbGroup = config.getNbGroups();
        this.groups = makeGroup();
        this.smtpClient = smtp;
    }
    
    /* Create nbGroup with the addresses. A group is composed at least of 3
    *  members: 1 sender and 2 receivers. They have to be different.
    *  A receiver can only receive one joke email in a "campaign". If there is
    *  not enough addresses to create min 3 members groups, throw an error that
    *  stop the application. */
    private List<Group> makeGroup() throws Exception
    {
        List<String> adresses = config.getAdresses();
        List<Group> allGroups = new ArrayList<>();
        List<Integer> check = new ArrayList<>();
        Random rand = new Random();
                
        // If there is not enough addresses
        if((adresses.size() / nbGroup) < 3)
        {
            throw new Exception("Not enough mail addresses");
            
        }
        else
        {
            // Create the groups randomly. The addresses already in the group
            // are marked and can't be choosen again
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
                // Add the new group to the class list of groups
                allGroups.add(group);
            }
        }        
        return allGroups;
    }
    
    // Select a message randomly
    public String selectMsg()
    {
        List<String> msg = config.getMsg();
        Random random = new Random();
        
        return msg.get(random.nextInt(msg.size()));
    }
    
    // Create a joke for each groups
    public List<Joke> createJokes()
    {
        List<Joke> jokes = new ArrayList<>();
        List<Person> members;
        Random r = new Random();
        int randSender;
        for(Group g : groups)
        {
            Joke j = new Joke();
            // Define randomly the sender 
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
    
    // Send the emails based on the jokes with the SMTP client
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