package edu.eci.pdsw.samples.entities;

import java.sql.Date;

/**
 * Esta clase son los votos que los usuarios pueden hacer por una iniciativa a
 * la que tengan algún interés o afinidad.
 * @author ECI-FACS
 */
public class Vote {
    
    private int id;
    private Date voteDate;
    private int affinity;
    
    /**
     * Constructor vacío de Vote
     */
    public Vote() {
    }
    
    /**
     * Constructor completo de Vote
     * @param id
     * @param voteDate
     * @param affinity 
     */
    public Vote(int id, Date voteDate, int affinity) {
        this.id = id;
        this.voteDate = voteDate;
        this.affinity = affinity;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getVoteDate() {
        return voteDate;
    }

    public void setVoteDate(Date voteDate) {
        this.voteDate = voteDate;
    }

    public int getAffinity() {
        return affinity;
    }

    public void setAffinity(int affinity) {
        this.affinity = affinity;
    }
    
    @Override
    public String toString() {
        return "Affinity{" + "id=" + id + ", voteDate=" + voteDate + ", affinity=" + affinity + '}';
    }
}
