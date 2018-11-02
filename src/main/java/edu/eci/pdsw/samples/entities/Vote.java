package edu.eci.pdsw.samples.entities;

import java.sql.Date;

/**
 * Esta clase son los votos que los usuarios pueden hacer por una iniciativa a
 * la que tengan algún interés o afinidad.
 * @author ECI-FACS
 */
public class Vote {
    
    private Date voteDate;
    private int affinity;
    
    /**
     * Constructor vacío de Vote
     */
    public Vote() {
    }
    
    /**
     * Constructor completo de Vote
     * @param voteDate
     * @param affinity 
     */
    public Vote(Date voteDate, int affinity) {
        this.voteDate = voteDate;
        this.affinity = affinity;
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
        return "Affinity{" + "voteDate=" + voteDate + ", affinity=" + affinity + '}';
    }
}
