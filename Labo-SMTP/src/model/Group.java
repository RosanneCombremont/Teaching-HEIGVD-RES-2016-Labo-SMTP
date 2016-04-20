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
public class Group {
    private final List<Person> victims = new ArrayList<>();
    
    public void addVictim(Person p)
    {
        victims.add(p);
    }
    public List<Person> getVictims()
    {
        return new ArrayList<>(victims);
    }
    
}
