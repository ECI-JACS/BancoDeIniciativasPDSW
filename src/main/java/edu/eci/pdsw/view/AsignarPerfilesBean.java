package edu.eci.pdsw.view;

import com.google.inject.Inject;
import edu.eci.pdsw.samples.entities.Area;
import java.io.IOException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import edu.eci.pdsw.samples.entities.Role;
import edu.eci.pdsw.samples.entities.User;
import edu.eci.pdsw.samples.services.ExceptionServiciosBancoIniciativas;
import edu.eci.pdsw.samples.services.ServiciosBancoIniciativas;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
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
    private String idArea;
    private List<SelectItem> listaAreas;
    private String nombres;
    private String apellidos;
    private String email;
    private String carnet;

    @PostConstruct
    public void init() {
        super.init();
        inicializarVariables();
    }

    public void inicializarVariables() {
        rol = "";
        rolUpdate = "";
        roles = new ArrayList<>();
        selectedUsuarios = new ArrayList<>();
        idArea = "";
        listaAreas = new ArrayList<>();
        nombres = "";
        apellidos = "";
        email = "";
        carnet = "";
    }

    public List<User> getUsuarios() {
        List<User> users = new ArrayList<>();
        try {
            int idA = 0;
            if (!idArea.equals("")) {
                idA = Integer.parseInt(idArea);
            }
            int carnetI = 0;
            if (!carnet.equals("")) {
                carnetI = Integer.parseInt(carnet);
            }
            users = serviciosBancoIniciativas.consultarUsuariosPorBusqueda(nombres,apellidos,email,carnetI,idA,rol);
        } catch (ExceptionServiciosBancoIniciativas ex) {
            System.out.println(ex.getMessage());
        }
        return users;
    }

    public void updateUsuarios() {
        try {
            for (User u : selectedUsuarios) {
                serviciosBancoIniciativas.actualizarRolUsuario(u.getEmail(), rolUpdate);
            }
        } catch (ExceptionServiciosBancoIniciativas ex) {
            System.out.println(ex.getMessage());
        }
        inicializarVariables();
    }

    public List<SelectItem> getRoles() throws IOException {
        Role[] rolesBD = Role.values();
        roles = new ArrayList<>();
        roles.clear();
        for (Role r : rolesBD) {
            SelectItem selecItem = new SelectItem(r);
            roles.add(selecItem);
        }
        return roles;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCarnet() {
        return carnet;
    }

    public void setCarnet(String carnet) {
        this.carnet = carnet;
    }

}
