package edu.eci.pdsw.view;

import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.faces.annotation.ManagedProperty;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpSession;

import com.google.inject.Inject;

import edu.eci.pdsw.samples.entities.Comment;
import edu.eci.pdsw.samples.entities.Initiative;
import edu.eci.pdsw.samples.entities.InitiativeStatus;
import edu.eci.pdsw.samples.entities.User;
import edu.eci.pdsw.samples.services.ExceptionServiciosBancoIniciativas;
import edu.eci.pdsw.samples.services.ServiciosBancoIniciativas;
import edu.eci.pdsw.samples.services.utilities.LoginSession;

/**
*
* @author ECI-JACS
*/
@SuppressWarnings("deprecation")
@ManagedBean(name = "commentBean")
@SessionScoped
public class CommentBean extends BasePageBean{
	
	
	private static final long serialVersionUID = 1L;
	
	@ManagedProperty(value = "#{param.iniciativa")
	private int iniciativa;
	
	@Inject
    private ServiciosBancoIniciativas serviciosBancoIniciativas;
	
	private Initiative iniciativaOri;
	private int idcomment;
	
	private List<Comment> comentarios ;
	
	/*
	@PostConstruct
    public void init() {
        super.init();
        try {
        	inicializarVariables();
        } catch (ExceptionServiciosBancoIniciativas ex) {
            Logger.getLogger(CommentBean.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }*/
	public void inicializarVariables() throws ExceptionServiciosBancoIniciativas{
		
		iniciativaOri = serviciosBancoIniciativas.consultarIniciativa(iniciativa);
		idcomment = getCommentId();
		comentarios = iniciativaOri.getComments();
		
	}
	
	public List<Comment> getCommentsIniciativa(){
		
		try {	
			inicializarVariables();
			//System.out.println(iniciativaOri.getComments().size());
			return iniciativaOri.getComments();
		} catch (ExceptionServiciosBancoIniciativas e) {
			e.printStackTrace();
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	
	public int getCommentId() {
        int idComentario = 0;
        try {
            idComentario = serviciosBancoIniciativas.consultarIdComentarios();
        } catch (ExceptionServiciosBancoIniciativas ex) {
            System.out.println(ex.getMessage());
        }
        return idComentario;
    }
	
	
	/*
	public void registrarComentario(String description) {
        HttpSession hs;
        try {
            hs = LoginSession.getSession();
            User usuario = (User) hs.getAttribute("usuario");           
            serviciosBancoIniciativas.insertarComentarioEnUnaIniciativa(new Comment(idcomment, description, new java.sql.Date(Calendar.getInstance().getTime().getTime()), usuario.getEmail(), iniciativa));
            inicializarVariables();
        } catch (ExceptionServiciosBancoIniciativas ex) {
            System.out.println(ex.getMessage());
        }
        
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro Exitoso", "--Iniciativa creada correctamente--"));
    }*/
	
	public int getIniciativa() {
		return iniciativa;
	}
	
	public void setIniciativa(int iniciativa) {
		this.iniciativa = iniciativa;
	}
	
	
	public void commentIniciativa(ActionEvent event) {
        this.iniciativa = (int) event.getComponent().getAttributes().get("parameter");
    }
}
