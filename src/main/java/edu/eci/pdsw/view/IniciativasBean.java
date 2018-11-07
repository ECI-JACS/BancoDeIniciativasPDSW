package edu.eci.pdsw.view;

import com.google.inject.Inject;
import edu.eci.pdsw.samples.entities.Initiative;
import edu.eci.pdsw.samples.entities.InitiativeStatus;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import edu.eci.pdsw.samples.services.ExceptionServiciosBancoIniciativas;
import edu.eci.pdsw.samples.services.ServiciosBancoIniciativas;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.faces.model.SelectItem;

/**
 *
 * @author ECI-JACS
 */
@SuppressWarnings("deprecation")
@ManagedBean(name = "iniciativasBean")
@SessionScoped
public class IniciativasBean extends BasePageBean {

    @Inject
    private ServiciosBancoIniciativas serviciosBancoIniciativas;
    
    private Initiative selectedIniciativa;
    private String idEstado;
    private List<SelectItem> estados;
    
    public void updateEstadoIniciativa() {
        try {
            serviciosBancoIniciativas.updateInitiativeStatus(selectedIniciativa.getId(),Integer.parseInt(idEstado));            
        } catch (ExceptionServiciosBancoIniciativas ex) {
            System.out.println(ex.getMessage());
        } catch (NumberFormatException ex) {
            System.out.println("Eso no es un id del estado de la iniciativa");
        }
    }

    public List<Initiative> getIniciativas() {
        List<Initiative> iniciativas = new ArrayList<>();
        try {
            iniciativas = serviciosBancoIniciativas.consultarIniciativas();
        } catch (ExceptionServiciosBancoIniciativas ex) {
            System.out.println(ex.getMessage());
        }
        return iniciativas;
    }
    
    public List<SelectItem> getEstados() throws IOException {
        estados = new ArrayList<>();
        estados.clear();
        try {
            List<InitiativeStatus> estadosBD = serviciosBancoIniciativas.consultarEstadosIniciativas();
            for(InitiativeStatus is : estadosBD){
                SelectItem selecItem = new SelectItem(is.getId(),is.getDescription());
                estados.add(selecItem);
            }            
        } catch (ExceptionServiciosBancoIniciativas ex) {
            System.out.println(ex.getMessage());
        }
        return estados;
    }
    
    public Initiative getSelectedIniciativa() {
        return this.selectedIniciativa;
    }
    
    public void setSelectedIniciativa(Initiative selectedIniciativa) {
        try{
            this.selectedIniciativa = selectedIniciativa;
        } catch (NullPointerException e) {
            System.out.println("Mientras se selecciona una iniciativa");
        }        
    }
    
    public String getIdEstado() {
        return idEstado;
    }
    
    public void setIdEstado(String idEstado) {
        this.idEstado = idEstado;
    }

}