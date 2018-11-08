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
@ManagedBean(name = "registroBean")
@SessionScoped
public class RegistroBean extends BasePageBean{

    @Inject
    private ServiciosBancoIniciativas serviciosBancoIniciativas;
    
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
            FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_WARN,"Código incorrecto. Sólo puede contener números.","Verifique el código."));
        }
    }
    
    public List<SelectItem> getListaAreas() throws IOException {
        listaAreas = new ArrayList<>();
        listaAreas.clear();
        try {
            List<Area> areasBD = serviciosBancoIniciativas.consultarAreas();
            for(Area a : areasBD){
                SelectItem selecItem = new SelectItem(a.getId(),a.getName());
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