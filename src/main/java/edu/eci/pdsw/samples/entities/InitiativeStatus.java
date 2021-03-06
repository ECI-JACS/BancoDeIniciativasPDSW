package edu.eci.pdsw.samples.entities;

/**
 * Esta clase representa los estados de las iniciativas.
 * @author ECI-FACS
 */
public class InitiativeStatus {
    
    private int id;
    private String description;

    /**
     * Constructor vacío de IniciativeStatus
     */
    public InitiativeStatus() {
    }
    
    /**
     * Constructor de IniciativeStatus completo
     * @param id
     * @param description 
     */
    public InitiativeStatus(int id, String description) {
        this.id = id;
        this.description = description;
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
    
    public boolean equals(InitiativeStatus iniStat){
    	return this.id == iniStat.id &&
    			this.description.equals(iniStat.description);
    }
    
    @Override
    public String toString() {
        return "IniciativeStatus{" + "id=" + id + ", description=" + description + "}";
    }
    
}
