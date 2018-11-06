package edu.eci.pdsw.view;

import java.io.IOException;
import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
//import javax.faces.bean.SessionScoped;
import javax.faces.bean.ApplicationScoped;
import javax.faces.context.FacesContext;

import edu.eci.pdsw.samples.entities.User;
import edu.eci.pdsw.samples.services.ExceptionServiciosBancoIniciativas;
import edu.eci.pdsw.samples.services.ServiciosBancoIniciativas;
import edu.eci.pdsw.samples.services.ServiciosBancoIniciativasFactory;

@SuppressWarnings("deprecation")
@ManagedBean(name = "loginBean")
@ApplicationScoped
public class LoginBean implements Serializable{
    
	//@Inject
	//private ServiciosBancoIniciativas serviciosBancoIniciativas;
	
        ServiciosBancoIniciativasFactory servicesInitiativesBankFactory = ServiciosBancoIniciativasFactory.getInstance();
        ServiciosBancoIniciativas serviciosBancoIniciativas = servicesInitiativesBankFactory.getServiciosBancoIniciativas();
	
	public void authentication(String email, String contrasena) throws IOException{
        try {        	
            User usuario = serviciosBancoIniciativas.consultarUsuario(email);             
            if (Integer.parseInt(contrasena) == usuario.getCode()) {
            	FacesContext.getCurrentInstance().getExternalContext().redirect("menu.xhtml"); 
            }else{
            	FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_WARN,"Contraseña o usuario incorrecto.","Ingrese el usuario y contraseña correcta"));            	
            }
        }catch (ExceptionServiciosBancoIniciativas ex) {            
            FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_WARN,"El usuario no existe.","Cree un usuario."));  
        } catch (NumberFormatException ex){
            FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_WARN,"Contraseña incorrecta.","Verifique la contraseña."));
        }       
        
    } 
}
