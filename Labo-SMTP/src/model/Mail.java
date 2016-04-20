/* *************************************************************************
 * HEIG-VD, Cours RES
 *
 * File        : Mail.java
 * Authors     : Combremont Rosanne & Ponce Kevin
 * Created on  : 20.04.2016
 *
 * Description : A basic and generic implementation of an email class
 *
 *
 **************************************************************************/
package model;


public class Mail {
    private String from;
    private String[] to;
    private String subject;
    private String message;
    
    public void setFrom(String from)
    {
        this.from = from;
    }
    public String getFrom()
    {
        return from;
    }
    
    public void setTo(String[] to)
    {
        this.to = to;
    }
    public String[] getTo()
    {
        return to;
    }
    
    public void setSubject(String subj)
    {
        this.subject = subj;
    }
    public String getSubject()
    {
        return subject;
    }
    
    public void setMessage(String message)
    {
        this.message = message;
    }
    public String getMessage()
    {
        return message;
    }
    
}
