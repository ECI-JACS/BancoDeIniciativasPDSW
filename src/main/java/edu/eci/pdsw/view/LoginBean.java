package edu.eci.pdsw.view;

import com.google.inject.Inject;
import edu.eci.pdsw.samples.entities.Role;
import java.io.IOException;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import edu.eci.pdsw.samples.entities.User;
import edu.eci.pdsw.samples.services.ExceptionServiciosBancoIniciativas;
import edu.eci.pdsw.samples.services.ServiciosBancoIniciativas;
import edu.eci.pdsw.samples.services.utilities.LoginSession;
import javax.servlet.http.HttpSession;

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
    private User usuario;

    public void authentication(String email, String contrasena) throws IOException {
        User usuarioTemp = new User();
        HttpSession hs;
        try {
            usuarioTemp = serviciosBancoIniciativas.consultarUsuario(email);
            if (Integer.toString(usuarioTemp.getCode()).equals(contrasena)) {
                hs = LoginSession.getSession();
                hs.setAttribute("usuario", usuarioTemp);
                FacesContext.getCurrentInstance().getExternalContext().redirect("menu.xhtml");
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Verifique", "--Usuario o contraseña incorrectos--"));
            }
        } catch (ExceptionServiciosBancoIniciativas ex) {
            System.out.println(ex.getMessage());
        } catch (NullPointerException ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Verifique", "--Usuario o contraseña incorrectos--"));
        }
        this.usuario = usuarioTemp;
    }
    
    public void logOut() throws IOException {
        HttpSession hs = LoginSession.getSession();
        hs.invalidate();
        FacesContext.getCurrentInstance().getExternalContext().redirect("inicio.xhtml");
    }
    
    /*######################################### Permisos para rol #########################################*/
    //Permitir modificar estado de las iniciativas 
    public boolean permitirModificarEstadoIniciativas() throws IOException{ 
        try{
            return usuario.getRole() == Role.ADMINISTRADOR || usuario.getRole() == Role.PMO;         
        }catch(NullPointerException ex){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Debe iniciar sesión.", "Inicie Sesión"));
            FacesContext.getCurrentInstance().getExternalContext().redirect("inicio.xhtml");           
        }       
        return usuario.getRole() == Role.ADMINISTRADOR || usuario.getRole() == Role.PMO;        
        
    }
    
    public boolean verIniciativasRelacionadas() throws IOException{ 
        try{
            return usuario.getRole() == Role.ADMINISTRADOR;          
        }catch(NullPointerException ex){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Debe iniciar sesión.", "Inicie Sesión"));
            FacesContext.getCurrentInstance().getExternalContext().redirect("inicio.xhtml");           
        }
        
        return usuario.getRole() == Role.ADMINISTRADOR;
        
    }
    
     /*######################################### ################ #########################################*/
    public User getUsuario() throws IOException {
        try{
            return usuario;            
        }catch(NullPointerException ex){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Debe iniciar sesión.", "Inicie Sesión"));
            FacesContext.getCurrentInstance().getExternalContext().redirect("inicio.xhtml");           
        }
        return usuario;
    }

    public void setUsuario(User usuario) {
        this.usuario = usuario;
    }
}
