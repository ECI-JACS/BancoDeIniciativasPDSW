
package edu.eci.pdsw.samples.entities;

import java.sql.Date;

/**
 *
 *
 */
public class Iniciativa {

 

    
    private int id;
    private int idEstado;
    private String descripcion;
    private String detalle;
    private Date fechaCreacion;
    private Usuario usuarioCreador;
    
    
    public Iniciativa(int id, int idEstado, String descripcion, String detalle, Date fechaCreacion, Usuario usuarioCreador) {
        this.id = id;
        this.idEstado = idEstado;
        this.descripcion= descripcion;
        this.detalle = detalle;
        this.fechaCreacion = fechaCreacion;
        this.usuarioCreador = usuarioCreador;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(int idEstado) {
        this.idEstado = idEstado;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Usuario getUsuarioCreador() {
        return usuarioCreador;
    }

    public void setUsuarioCreador(Usuario usuarioCreador) {
        this.usuarioCreador = usuarioCreador;
    }

    @Override
    public String toString() {
        return "iniciativa{" + "id=" + id + ", idEstado=" + idEstado + ", descripcion=" + descripcion + ", detalle=" + detalle + ", fechaCreacion=" + fechaCreacion + ", usuarioCreador=" + usuarioCreador + '}';
    }
  
    
}
