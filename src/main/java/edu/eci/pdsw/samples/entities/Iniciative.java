package edu.eci.pdsw.samples.entities;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * Esta clase son las iniciativas o ideas propuestas en el Banco de Iniciativas.
 * @author ECI-FACS
 */
public class Iniciative {

    private int id;
    private String description;
    private String detail;
    private Date creationDate;
    private Date modificationDate;
    private Usuario user;
    private IniciativeStatus iniciativeStatus;
    private List<String> keyWords;
    private List<String> comments;
    private List<Vote> votes;
    
    /**
     * Constructor vacío de Iniciative
     */
    public Iniciative(){
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
     * @param iniciativeStatus 
     */
    public Iniciative(int id, String description, String detail, Date creationDate, Date modificationDate, List<String> keyWords, Usuario user, IniciativeStatus iniciativeStatus) {
        this.id = id;
        this.description = description;
        this.detail = detail;
        this.creationDate = creationDate;
        this.modificationDate = modificationDate;
        this.user = user;
        this.iniciativeStatus = iniciativeStatus;
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
     * @param iniciativeStatus
     * @param votes 
     */
    public Iniciative(int id, String description, String detail, Date creationDate, Date modificationDate, List<String> keyWords, List<String> comments, Usuario user, IniciativeStatus iniciativeStatus, List<Vote> votes) {
        this.id = id;
        this.description = description;
        this.detail = detail;
        this.creationDate = creationDate;
        this.modificationDate = modificationDate;
        this.keyWords = keyWords;
        this.comments = comments;
        this.user = user;
        this.iniciativeStatus = iniciativeStatus;
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

    public Usuario getUser() {
        return user;
    }

    public void setUser(Usuario user) {
        this.user = user;
    }

    public IniciativeStatus getIniciativeStatus() {
        return iniciativeStatus;
    }

    public void setIniciativeStatus(IniciativeStatus iniciativeStatus) {
        this.iniciativeStatus = iniciativeStatus;
    }

    public List<String> getKeyWords() {
        return keyWords;
    }

    public void setKeyWords(List<String> keyWords) {
        this.keyWords = keyWords;
    }

    public List<String> getComments() {
        return comments;
    }

    public void setComments(List<String> comments) {
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
        return "Iniciative{" + "id=" + id + ", description=" + description + ", detail=" + detail + ", creationDate=" + creationDate + ", modificationDate=" + modificationDate + ", user=" + user + ", iniciativeStatus=" + iniciativeStatus + ", keyWords=" + keyWords + ", comments=" + comments + ", votes=" + votes + '}';
    }
}
