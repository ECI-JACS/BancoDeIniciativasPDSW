package edu.eci.pdsw.view;

import com.google.inject.Inject;
import edu.eci.pdsw.samples.entities.Initiative;
import edu.eci.pdsw.samples.entities.InitiativeStatus;
import edu.eci.pdsw.samples.entities.User;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import edu.eci.pdsw.samples.services.ExceptionServiciosBancoIniciativas;
import edu.eci.pdsw.samples.services.ServiciosBancoIniciativas;
import java.sql.Date;
import java.util.Calendar;

/**
 *
 * @author ECI-JACS
 */
@SuppressWarnings("deprecation")
@ManagedBean(name = "registroIniciativaBean")
@SessionScoped
public class RegistroIniciativaBean extends BasePageBean {

    @Inject
    private ServiciosBancoIniciativas serviciosBancoIniciativas;

    public int getIniciativaId() {
        int idIniciativa = 0;
        try {
            idIniciativa = serviciosBancoIniciativas.consultarIdIniciativa();
        } catch (ExceptionServiciosBancoIniciativas ex) {
            System.out.println(ex.getMessage());
        }
        return idIniciativa;
    }

    public void registrarIniciativa(String description, String detail, String email, String kewWords) {
        try {
            User user = serviciosBancoIniciativas.consultarUsuario(email);
            int id = serviciosBancoIniciativas.consultarIdIniciativa();
            serviciosBancoIniciativas.registrarIniciativa(new Initiative(id, description, detail, new Date(Calendar.getInstance().getTime().getTime()), null, kewWords, user, new InitiativeStatus(1, "En espera de revisión")));
        } catch (ExceptionServiciosBancoIniciativas ex) {
            System.out.println(ex.getMessage());
        }
    }

}
