/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.pdsw.samples.entities;

import java.sql.Date;

/**
 *
 * @author 2133541
 */
public class Interest {
    private Initiative initiative;
    private User user;
    private String comment;
    private Date date;
    private int interestLevel;

    public Interest(Initiative initiative, User user, String comment, Date date, int interestLevel) {
        this.initiative = initiative;
        this.user = user;
        this.comment = comment;
        this.date = date;
        this.interestLevel = interestLevel;
    }
    
        public Initiative getInitiative() {
        return initiative;
    }

    public void setInitiative(Initiative initiative) {
        this.initiative = initiative;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getInterestNevel() {
        return interestLevel;
    }

    public void setInterestNevel(int interestLevel) {
        this.interestLevel = interestLevel;
    }

    @Override
    public String toString() {
        return "Interest{" + "initiative=" + initiative + ", user=" + user + ", comment=" + comment + ", date=" + date + ", interestLevel=" + interestLevel + '}';
    }
    
    
    
}
