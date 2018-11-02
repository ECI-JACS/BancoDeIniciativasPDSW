package edu.eci.pdsw.samples.entities;

import java.sql.Date;
 

/**
 *
 * @author ECI-JACS
 */
public class User {  
    private String names;
    private String lastNames;
    private String email;
    private int code;
    private String state;
    private Role role;
    private Area area;

    public User() {
    }

    public User(String names, String lastNames, String email, int code, String state, Role role, Area area) {
        this.names = names;
        this.lastNames = lastNames;
        this.email = email;
        this.code = code;
        this.state = state;
        this.role = role;
        this.area = area;
    }

    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
    }

    public String getLastNames() {
        return lastNames;
    }

    public void setLastNames(String lastNames) {
        this.lastNames = lastNames;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

   @Override
    public String toString() {
        return "Usuario{" + "names=" + names + ", lastNames=" + lastNames + ", email=" + email + ", code=" + code + ", estado=" + state + ", role=" + role + ", area=" + area + '}';
    }
}
