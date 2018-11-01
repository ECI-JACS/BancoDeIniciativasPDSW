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
public class Interes {


    private Iniciativa iniciativa;
    private Usuario usuario;
    private String comentario;
    private Date fecha;
    private int nivelInteres;

    public Interes(Iniciativa iniciativa, Usuario usuario, String comentario, Date fecha, int nivelInteres) {
        this.iniciativa = iniciativa;
        this.usuario = usuario;
        this.comentario = comentario;
        this.fecha = fecha;
        this.nivelInteres = nivelInteres;
    }
    
        public Iniciativa getIniciativa() {
        return iniciativa;
    }

    public void setIniciativa(Iniciativa iniciativa) {
        this.iniciativa = iniciativa;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getNivelInteres() {
        return nivelInteres;
    }

    public void setNivelInteres(int nivelInteres) {
        this.nivelInteres = nivelInteres;
    }

    @Override
    public String toString() {
        return "Interes{" + "iniciativa=" + iniciativa + ", usuario=" + usuario + ", comentario=" + comentario + ", fecha=" + fecha + ", nivelInteres=" + nivelInteres + '}';
    }
    
    
    
}
