/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author rosanne
 */
public class Person {
    private String lastName;
    private String firstName;
    private String mailAddress;
    
    public Person(String lastName, String firstName, String addr)
    {
        this.lastName = lastName;
        this.firstName = firstName;
        this.mailAddress = addr;
    }
    public Person(String addr)
    {
        int point = addr.indexOf('.');
        int at = addr.indexOf('@');
        this.lastName = addr.substring(point + 1, at);
        this.firstName = addr.substring(0, point);
        this.mailAddress = addr;
    }
    
    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }
    public String getLastName()
    {
        return lastName;
    }
    
    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }
    public String getFirstName()
    {
        return firstName;
    }
    
    public void setMailAddress(String addr)
    {
        this.mailAddress = addr;
    }
    public String getMailAddress()
    {
        return mailAddress;
    }
    
}
