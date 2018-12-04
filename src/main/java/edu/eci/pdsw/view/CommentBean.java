package edu.eci.pdsw.view;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.google.inject.Inject;

import edu.eci.pdsw.samples.entities.Comment;
import edu.eci.pdsw.samples.entities.Initiative;
import edu.eci.pdsw.samples.entities.User;
import edu.eci.pdsw.samples.services.ExceptionServiciosBancoIniciativas;
import edu.eci.pdsw.samples.services.ServiciosBancoIniciativas;
import edu.eci.pdsw.samples.services.utilities.LoginSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author ECI-JACS
 */
@SuppressWarnings("deprecation")
@ManagedBean(name = "commentBean")
@SessionScoped
public class CommentBean extends BasePageBean {

    @Inject
    private ServiciosBancoIniciativas serviciosBancoIniciativas;

    private Initiative iniciativa;
    private User usuarioComentario;
    private List<Comment> comentarios;

    /*
    private Initiative iniciativaOri;
    private int idcomment;
     */
    public void verComentarios() throws IOException {
        FacesContext.getCurrentInstance().getExternalContext().redirect("comments.xhtml");
    }

    public List<Comment> getComentarios() {
        comentarios = iniciativa.getComments();
        return comentarios;
    }

    public void establecerUsuarioComentario(String emailUsuario) {
        try {
            usuarioComentario = serviciosBancoIniciativas.consultarUsuario(emailUsuario);
        } catch (ExceptionServiciosBancoIniciativas ex) {
            Logger.getLogger(CommentBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @PostConstruct
    public void init() {
        super.init();
        this.inicializarVariables();
    }

    public void inicializarVariables() {
        this.iniciativa = new Initiative();
        this.usuarioComentario = new User();
        this.comentarios = new ArrayList<>();
    }

    /*
    public List<Comment> getCommentsIniciativa() {
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
     */
    public int getCommentId() {
        int idComentario = 0;
        try {
            idComentario = serviciosBancoIniciativas.consultarIdComentarios();
        } catch (ExceptionServiciosBancoIniciativas ex) {
            System.out.println(ex.getMessage());
        }
        return idComentario;
    }

    public void registrarComentario(String comentario) {
        try {
            HttpSession hs = LoginSession.getSession();
            User usuario = (User) hs.getAttribute("usuario");
            serviciosBancoIniciativas.insertarComentarioEnUnaIniciativa(new Comment(serviciosBancoIniciativas.consultarIdComentarios(), comentario, new java.sql.Date(Calendar.getInstance().getTime().getTime()), usuario.getEmail(), this.iniciativa));
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro Exitoso", "--Comentario creado correctamente--"));
        } catch (ExceptionServiciosBancoIniciativas ex) {
            System.out.println(ex.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Falló Registro", "--El comentario no pudo ser agregado--"));
        }
    }

    /*
    public int getIniciativa() {
        return iniciativa;
    }

    public void setIniciativa(int iniciativa) {
        this.iniciativa = iniciativa;
    }

    public void commentIniciativa(ActionEvent event) {
        this.iniciativa = (int) event.getComponent().getAttributes().get("parameter");
    }
     */
    public Initiative getIniciativa() {
        return iniciativa;
    }

    public void setIniciativa(Initiative iniciativa) {
        this.iniciativa = iniciativa;
    }

    public User getUsuarioComentario() {
        return usuarioComentario;
    }

    public void setUsuarioComentario(User usuarioComentario) {
        this.usuarioComentario = usuarioComentario;
    }
    
}
