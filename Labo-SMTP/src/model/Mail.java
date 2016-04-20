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
