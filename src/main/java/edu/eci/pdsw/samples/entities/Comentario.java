package edu.eci.pdsw.samples.entities;

import java.sql.Date;

/**
 * 
 * @author ECI-JACS
 */
public class Usuario {
    
    private int id;
    private Date fecha;
    private String comentario;
    private int puntaje;
    
    private Suscriptor suscriptor;


    public Comentario() {
    }

    public Comentario(int id, Date fecha, String comentario, int puntaje, Suscriptor suscriptor) {
        this.id = id;
        this.fecha = fecha;
        this.comentario = comentario;
        this.puntaje = puntaje;
        this.suscriptor = suscriptor;
    }

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public int getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(int puntaje) {
        this.puntaje = puntaje;
    }

    public Suscriptor getSuscriptor() {
        return suscriptor;
    }

    public void setSuscriptor(Suscriptor suscriptor) {
        this.suscriptor = suscriptor;
    }

    @Override
    public String toString() {
        return "Comentario{" + "id=" + id + ", fecha=" + fecha + ", comentario=" + comentario + ", puntaje=" + puntaje + ", suscriptor=" + suscriptor + '}';
    }

    
}
