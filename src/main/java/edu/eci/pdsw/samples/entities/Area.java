package edu.eci.pdsw.samples.entities;

/**
 * Esta clase representa las áreas o dependencias a las que pertenece un usuario.
 * @author ECI-JACS
 */
public class Area {
    
    private int id;
    private String name;
    
    /**
     * Constructor vacío de Area
     */
    public Area(){
    }
    
    /**
     * Constructor de Area completo
     * @param id
     * @param name 
     */
    public Area(int id, String name){
        this.id = id;
        this.name = name;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean equals(Area area){
    	return this.id == area.id &&
    			this.name.equals(area.name);
    }
    
    @Override
    public String toString() {
        return "Area{" + "id=" + id + ", name=" + name + "}";
    }
}
