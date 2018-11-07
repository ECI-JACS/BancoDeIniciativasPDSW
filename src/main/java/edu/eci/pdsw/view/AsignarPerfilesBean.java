package edu.eci.pdsw.view;

import com.google.inject.Inject;
import java.io.IOException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import edu.eci.pdsw.samples.entities.Area;
import edu.eci.pdsw.samples.entities.Role;
import edu.eci.pdsw.samples.entities.User;
import edu.eci.pdsw.samples.entities.UserStatus;
import edu.eci.pdsw.samples.services.ExceptionServiciosBancoIniciativas;
import edu.eci.pdsw.samples.services.ServiciosBancoIniciativas;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
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
    private List<SelectItem> roles;
    
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

}
