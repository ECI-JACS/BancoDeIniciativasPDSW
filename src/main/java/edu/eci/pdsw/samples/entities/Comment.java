package edu.eci.pdsw.samples.entities;

import java.sql.Date;

/**
 * Esta clase representa los comentarios que los usuarios pueden dejar a una 
 * o varias iniciativas.
 * @author ECI-JACS
 */
public class Comment {

    private int id;
    private String description;
    private Date commentDate;
    private String userEmail;
    private Initiative initiative;
    

    public Comment() {
    }

    public Comment(int id, String description, Date commentDate, String userEmail, Initiative initiative) {
        this.id = id;
        this.description = description;
        this.commentDate = commentDate;
        this.userEmail = userEmail;
        this.initiative = initiative;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(Date commentDate) {
        this.commentDate = commentDate;
    }
    
    public String getUserEmail() {
    	return userEmail;
    }
    
    public void setUserEmail(String userEmail) {
    	this.userEmail = userEmail;
    }
    
    public Initiative getInitiative() {
    	return initiative;
    }
    
    public void setInitiative(Initiative initiative) {
    	this.initiative = initiative;
    }

    @Override
    public String toString() {
        return "Interest{" + "id=" + id + ", description=" + description + ", commentDate=" + commentDate + ",usuario"+ userEmail +"}\n";
    }

}
