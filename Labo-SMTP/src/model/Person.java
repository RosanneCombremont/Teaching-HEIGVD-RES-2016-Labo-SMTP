/* *************************************************************************
 * HEIG-VD, Cours RES
 *
 * File        : Person.java
 * Authors     : Combremont Rosanne & Ponce Kevin
 * Created on  : 20.04.2016
 *
 * Description : Class that defines a person by its fist name, last name and
 *               email address.
 *
 *
 **************************************************************************/
package model;


public class Person {
    private String lastName;
    private String firstName;
    private String mailAddress;
    
    // Constructor
    public Person(String lastName, String firstName, String addr)
    {
        this.lastName = lastName;
        this.firstName = firstName;
        this.mailAddress = addr;
    }
    
    // Constructor that is usable only when the email address is composed this
    // way: firstname.lastname@domain.com
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
