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

/**
 *
 * @author hcadavid
 */
public class Suscriptor {

    private int id;
    private String nombre;
    private int anoNacimiento;
    private int numeroSuscripciones;

    public Suscriptor(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public Suscriptor() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getAnoNacimiento() {
        return anoNacimiento;
    }

    public void setAnoNacimiento(int anoNacimiento) {
        this.anoNacimiento = anoNacimiento;
    }

    public int getNumeroSuscripciones() {
        return numeroSuscripciones;
    }

    public void setNumeroSuscripciones(int numeroSuscripciones) {
        this.numeroSuscripciones = numeroSuscripciones;
    }

    @Override
    public String toString() {
        return "Suscriptor{" + "id=" + id + ", nombre=" + nombre + ", anoNacimiento=" + anoNacimiento + ", numeroSuscripciones=" + numeroSuscripciones + '}';
    }    
    
}
