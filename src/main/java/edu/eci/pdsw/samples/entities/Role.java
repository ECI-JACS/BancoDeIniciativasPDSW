/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.pdsw.samples.entities;

/**
 *
 * @author 2133541
 */
public class Role {
    
    
    private int id;
    private String role;
    
    public Role(int id, String role){
        this.id  = id;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Rol{" + "id=" + id + ", role=" + role + '}';
    }
    
    
    
    
}
