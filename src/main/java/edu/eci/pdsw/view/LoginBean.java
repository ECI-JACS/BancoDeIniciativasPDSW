package edu.eci.pdsw.view;

import com.google.inject.Inject;
import edu.eci.pdsw.samples.entities.Area;
import edu.eci.pdsw.samples.entities.Role;
import java.io.IOException;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import edu.eci.pdsw.samples.entities.User;
import edu.eci.pdsw.samples.entities.UserStatus;
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
@ManagedBean(name = "loginBean")
@SessionScoped
public class LoginBean extends BasePageBean {

    @Inject
    private ServiciosBancoIniciativas serviciosBancoIniciativas;

    public void authentication(String email, String contrasena) throws IOException {
        try {
            User usuario = serviciosBancoIniciativas.consultarUsuario(email);
            if (usuario.getRole() == Role.SIN_ASIGNAR){
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "El usuario no tiene rol asignado.", "Comunìquese con el administrador."));
            }
            else if (Integer.parseInt(contrasena) == usuario.getCode()) {
                FacesContext.getCurrentInstance().getExternalContext().redirect("menu.xhtml");
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Contraseña o usuario incorrecto.", "Ingrese el usuario y contraseña correcta"));
            }
        } catch (ExceptionServiciosBancoIniciativas ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "El usuario no existe.", "Cree un usuario."));
        } catch (NumberFormatException ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Contraseña incorrecta.", "Verifique la contraseña."));
        } 
    }

    private String idArea;
    private List<SelectItem> listaAreas;

    public void registrarUsuario(String names, String lastNames, String email, String code) {
        try {
            Area area = serviciosBancoIniciativas.consultarArea(Integer.parseInt(idArea));
            User user = new User(names, lastNames, email, Integer.parseInt(code), UserStatus.ACTIVO, Role.SIN_ASIGNAR, area);
            serviciosBancoIniciativas.registrarUsuario(user);
        } catch (ExceptionServiciosBancoIniciativas ex) {
            System.out.println(ex.getMessage());
        } catch (NumberFormatException ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Código incorrecto. Sólo puede contener números.", "Verifique el código."));
        }
    }

    public List<SelectItem> getListaAreas() throws IOException {
        listaAreas = new ArrayList<>();
        listaAreas.clear();
        try {
            List<Area> areasBD = serviciosBancoIniciativas.consultarAreas();
            for (Area a : areasBD) {
                SelectItem selecItem = new SelectItem(a.getId(), a.getName());
                listaAreas.add(selecItem);
            }
        } catch (ExceptionServiciosBancoIniciativas ex) {
            System.out.println(ex.getMessage());
        }
        return listaAreas;
    }

    public String getIdArea() {
        return idArea;
    }

    public void setIdArea(String idArea) {
        this.idArea = idArea;
    }
}
