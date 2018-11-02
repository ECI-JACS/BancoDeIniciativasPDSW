package edu.eci.pdsw.samples.entities;

import java.sql.Date;
 

/**
 *
 * @author ECI-JACS
 */
public class Usuario {

    

    private String nombres;
    private String apellidos;
    private String correo;
    private int codigo;
    private String estado;
    private Rol rol;
    private Area area;

    public Usuario() {
    }

    public Usuario(String nombres, String apellidos, String correo, int codigo, String estado, Rol rol, Area area) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.correo = correo;
        this.codigo = codigo;
        this.estado = estado;
        this.rol = rol;
        this.area = area;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

   @Override
    public String toString() {
        return "Usuario{" + "nombres=" + nombres + ", apellidos=" + apellidos + ", correo=" + correo + ", codigo=" + codigo + ", estado=" + estado + ", rol=" + rol + ", area=" + area + '}';
    }
}
