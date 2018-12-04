package edu.eci.pdsw.view;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.google.inject.Inject;

import edu.eci.pdsw.samples.entities.Comment;
import edu.eci.pdsw.samples.entities.Initiative;
import edu.eci.pdsw.samples.entities.User;
import edu.eci.pdsw.samples.entities.Vote;
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
@ManagedBean(name = "voteBean")
@SessionScoped
public class VoteBean extends BasePageBean {

    @Inject
    private ServiciosBancoIniciativas serviciosBancoIniciativas;

    private Initiative iniciativa;

    @PostConstruct
    public void init() {
        super.init();
        this.inicializarVariables();
    }

    public void inicializarVariables() {
        this.iniciativa = new Initiative();
    }

    private int consultarIdVotos() {
        int idVoto = 0;
        try {
            idVoto = serviciosBancoIniciativas.consultarIdVotos();
        } catch (ExceptionServiciosBancoIniciativas ex) {
            Logger.getLogger(VoteBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return idVoto;
    }

    public void like() {
        HttpSession hs = LoginSession.getSession();
        User usuario = (User) hs.getAttribute("usuario");
        try {
            serviciosBancoIniciativas.votar(new Vote(consultarIdVotos(), new java.sql.Date(Calendar.getInstance().getTime().getTime()), 5, usuario.getEmail(), this.iniciativa.getId()));
        } catch (ExceptionServiciosBancoIniciativas ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Voto fallido", "--Ya ha votado por esta iniciativa--"));
        }
    }

    public void dislike() {
        try {
            HttpSession hs = LoginSession.getSession();
            User usuario = (User) hs.getAttribute("usuario");
            serviciosBancoIniciativas.quitarVoto(usuario.getEmail(), this.iniciativa.getId());
        } catch (ExceptionServiciosBancoIniciativas ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Voto fallido", "--No ha votado por esta iniciativa--"));
        }
    }

    public Initiative getIniciativa() {
        return iniciativa;
    }

    public void setIniciativa(Initiative iniciativa) {
        this.iniciativa = iniciativa;
    }

}
