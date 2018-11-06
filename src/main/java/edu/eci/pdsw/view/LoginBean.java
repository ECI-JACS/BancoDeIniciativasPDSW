package edu.eci.pdsw.view;

import java.io.IOException;
import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;

import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import edu.eci.pdsw.samples.entities.User;
import edu.eci.pdsw.samples.services.ExceptionServiciosBancoIniciativas;
import edu.eci.pdsw.samples.services.ServiciosBancoIniciativas;

@SuppressWarnings("deprecation")
@ManagedBean(name = "loginBean")
@SessionScoped
public class LoginBean implements Serializable{

	private static final long serialVersionUID = 1L;

	@Inject
	private ServiciosBancoIniciativas serviciosBancoIniciativas;
	
	//@ManagedProperty(value = "#{param.documento}")
	//private long documento;	
	
	public void authentication(String email, String contraseña) throws Exception{
        try {
        	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", "Validación Correcta"));
        	
            User usuario = serviciosBancoIniciativas.consultarUsuario(email);             
            if (Integer.parseInt(contraseña) == usuario.getCode()) {
            	FacesContext.getCurrentInstance().getExternalContext().redirect("menu.xhtml"); 
            }else{
            	FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_WARN,"Contraseña o usuario incorrecto","Ingrese el usuario y contraseña correcta"));            	
            }
        } catch (ExceptionServiciosBancoIniciativas ex) {            
        	FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_WARN,"Contraseña o usuario incorrecto","Ingrese el usuario y contraseña correcta"));
        }        
        
    } 
	
	public void action() throws IOException {
	    FacesContext.getCurrentInstance().getExternalContext().dispatch("another.xhtml");
	}
	
	public String mensaje() throws IOException{
		return "menu.xhtml";
		//FacesContext.getCurrentInstance().getExternalContext().redirect("menu.xhtml");		
        //FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_WARN,"Contraseña o usuario incorrecto","Ingrese el usuario y contraseña correcta"));  
    } 
}
