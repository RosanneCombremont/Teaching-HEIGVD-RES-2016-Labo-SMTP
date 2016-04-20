/* *************************************************************************
 * HEIG-VD, Cours RES
 *
 * File        : Group.java
 * Authors     : Combremont Rosanne & Ponce Kevin
 * Created on  : 20.04.2016
 *
 * Description : Class Group represents a group of Person.
 *
 *
 **************************************************************************/
package model;
import java.util.ArrayList;
import java.util.List;


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
