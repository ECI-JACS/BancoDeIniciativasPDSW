package edu.eci.pdsw.view;

import com.google.inject.Inject;
import java.io.IOException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import edu.eci.pdsw.samples.entities.Role;
import edu.eci.pdsw.samples.entities.User;
import edu.eci.pdsw.samples.services.ExceptionServiciosBancoIniciativas;
import edu.eci.pdsw.samples.services.ServiciosBancoIniciativas;

import java.util.ArrayList;
import java.util.List;
import javax.faces.model.SelectItem;

/**
 *
 * @author ECI-JACS
 */
@SuppressWarnings("deprecation")
@ManagedBean(name = "asignarPerfilesBean")
@SessionScoped
public class AsignarPerfilesBean extends BasePageBean {

    @Inject
    private ServiciosBancoIniciativas serviciosBancoIniciativas;

    private String rol;
    private String rolUpdate;    
    private List<SelectItem> roles;
    private List<User> selectedUsuarios;    
    
    public List<User> getUsuarios() {
        List<User> users = new ArrayList<>();
        try {            
            users = serviciosBancoIniciativas.consultarUsuariosRol(Role.valueOf(rol));
        } catch (ExceptionServiciosBancoIniciativas ex) {
            System.out.println(ex.getMessage());
        } catch (NullPointerException ex) {
            System.out.println("Mientras se selecciona un rol");
        }
        return users;
    }
    
    public void updateUsuarios() {
        try {   
            System.out.println("################# Rol cambio: "+rolUpdate);            
            for(User u : selectedUsuarios){
                System.out.println("################# Usuario: "+u);
                serviciosBancoIniciativas.actualizarRolUsuario(u.getEmail(), rolUpdate);
            }            
        } catch (ExceptionServiciosBancoIniciativas ex) {
            System.out.println(ex.getMessage());
        } catch (NullPointerException ex) {
            System.out.println("Error al seleccionar un rol");
        }
    }
    
    public List<SelectItem> getRoles() throws IOException {
        Role[] rolesBD = Role.values();
        roles = new ArrayList<>();
        roles.clear();
        for (Role r : rolesBD) {
            System.out.println(r);
            SelectItem selecItem = new SelectItem(r);
            roles.add(selecItem);
        }
        return roles;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
    
    public String getRolUpdate() {
        return rolUpdate;
    }

    public void setRolUpdate(String rolUpdate) {
        this.rolUpdate = rolUpdate;
    }
    
     public List<User> getSelectedUsuarios() {
        return selectedUsuarios;
    }

    public void setSelectedUsuarios(List<User> selectedUsuarios) {
        this.selectedUsuarios = selectedUsuarios;
    }

}
