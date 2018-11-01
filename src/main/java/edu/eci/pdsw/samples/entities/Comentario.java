/*
 * Copyright (C) 2015 hcadavid
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package edu.eci.pdsw.samples.entities;

import java.sql.Date;

/**
 *
 * @author hcadavid
 */
public class Comentario {
    
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
