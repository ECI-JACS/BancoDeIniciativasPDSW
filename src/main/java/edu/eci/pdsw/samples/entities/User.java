package edu.eci.pdsw.samples.entities;

/**
 *
 * @author ECI-JACS
 */
public class User {

    private String names;
    private String lastNames;
    private String email;
    private int code;
    private UserStatus status;
    private Role role;
    private Area area;

    public User() {
    }

    public User(String names, String lastNames, String email, int code, UserStatus status, Role role, Area area) {
        this.names = names;
        this.lastNames = lastNames;
        this.email = email;
        this.code = code;
        this.status = status;
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

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }

    public Role getRole() {
        return role;
    }
    
    public String getRoleString() {
        return role.toString();
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
        return "Usuario{" + "names=" + names + ", lastNames=" + lastNames + ", email=" + email + ", code=" + code + ", status=" + status + ", role=" + role + ", area=" + area + "}";
    }
    
    public boolean equals(User user) {
    	return this.names.equals(user.names) &&
    			this.lastNames.equals(user.lastNames) &&
    			this.email.equals(user.email) &&
    			this.code == user.code &&
    			this.status.equals(user.status) &&
    			this.area.equals(user.area) &&
    			this.role.equals(user.role);
    	
    }
    
}
