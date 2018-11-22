package edu.eci.pdsw.samples.entities;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * Esta clase son las iniciativas o ideas propuestas en el Banco de Iniciativas.
 * @author ECI-FACS
 */
public class Initiative {

    private int id;
    private String description;
    private String detail;
    private Date creationDate;
    private Date modificationDate;
    private User user;
    private InitiativeStatus initiativeStatus;
    private String keyWords;
    private List<Comment> comments;
    private List<Vote> votes;
    
    /**
     * Constructor vacío de Iniciative
     */
    public Initiative(){
    }
    
    /**
     * Constructor de Iniciative sin comentarios ni votos
     * @param id
     * @param description
     * @param detail
     * @param creationDate
     * @param modificationDate
     * @param keyWords
     * @param user
     * @param initiativeStatus 
     */
    public Initiative(int id, String description, String detail, Date creationDate, Date modificationDate, String keyWords, User user, InitiativeStatus initiativeStatus) {
        this.id = id;
        this.description = description;
        this.detail = detail;
        this.creationDate = creationDate;
        this.modificationDate = modificationDate;
        this.user = user;
        this.initiativeStatus = initiativeStatus;
        this.keyWords = keyWords;
        this.comments = new ArrayList();
        this.votes = new ArrayList();
    }
    
    /**
     * Constructor de Iniciative completo
     * @param id
     * @param description
     * @param detail
     * @param creationDate
     * @param modificationDate
     * @param keyWords
     * @param comments
     * @param user
     * @param initiativeStatus
     * @param votes 
     */
    public Initiative(int id, String description, String detail, Date creationDate, Date modificationDate, String keyWords, List<Comment> comments, User user, InitiativeStatus initiativeStatus, List<Vote> votes) {
        this.id = id;
        this.description = description;
        this.detail = detail;
        this.creationDate = creationDate;
        this.modificationDate = modificationDate;
        this.keyWords = keyWords;
        this.comments = comments;
        this.user = user;
        this.initiativeStatus = initiativeStatus;
        this.votes = votes;
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

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getModificationDate() {
        return modificationDate;
    }

    public void setModificationDate(Date modificationDate) {
        this.modificationDate = modificationDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public InitiativeStatus getIniciativeStatus() {
        return initiativeStatus;
    }

    public void setIniciativeStatus(InitiativeStatus iniciativeStatus) {
        this.initiativeStatus = iniciativeStatus;
    }

    public String getKeyWords() {
        return keyWords;
    }
    
    public String showKeyWords() {
        String keyWords2 = keyWords.replace(",",", ");
        return keyWords2;
    }

    public void setKeyWords(String keyWords) {
        this.keyWords = keyWords;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public List<Vote> getVotes() {
        return votes;
    }

    public void setVotes(List<Vote> votes) {
        this.votes = votes;
    }  
    
    @Override
    public String toString() {
        return "Iniciative{" + "id=" + id + ", description=" + description + ", detail=" + detail + ", creationDate=" + creationDate + ", modificationDate=" + modificationDate + ", user=" + user + ", initiativeStatus=" + initiativeStatus + ", keyWords=" + keyWords + ", comments=" + comments + ", votes=" + votes + '}';
    }
}
