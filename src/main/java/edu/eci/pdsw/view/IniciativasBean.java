package edu.eci.pdsw.view;

import com.google.inject.Inject;
import edu.eci.pdsw.samples.entities.Initiative;
import edu.eci.pdsw.samples.entities.InitiativeStatus;
import edu.eci.pdsw.samples.entities.User;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import edu.eci.pdsw.samples.services.ExceptionServiciosBancoIniciativas;
import edu.eci.pdsw.samples.services.ServiciosBancoIniciativas;
import edu.eci.pdsw.samples.services.utilities.LoginSession;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpSession;
import org.primefaces.event.ItemSelectEvent;
import org.primefaces.model.chart.PieChartModel;

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
    private String palabrasClave;
    private List<SelectItem> estados;
    
    private PieChartModel pieModel;
    
    public int getIniciativaId() {
        int idIniciativa = 0;
        try {
            idIniciativa = serviciosBancoIniciativas.consultarIdIniciativa();
        } catch (ExceptionServiciosBancoIniciativas ex) {
            System.out.println(ex.getMessage());
        }
        return idIniciativa;
    }

    public void registrarIniciativa(String description, String detail, String kewWords) {
        HttpSession hs;
        try {
            hs = LoginSession.getSession();
            User usuario = (User) hs.getAttribute("usuario");
            int id = serviciosBancoIniciativas.consultarIdIniciativa();
            serviciosBancoIniciativas.registrarIniciativa(new Initiative(id, description, detail, new Date(Calendar.getInstance().getTime().getTime()), null, kewWords, usuario, new InitiativeStatus(1, "En espera de revisión")));
        } catch (ExceptionServiciosBancoIniciativas ex) {
            System.out.println(ex.getMessage());
        }
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro Exitoso", "--Iniciativa creada correctamente--"));        
    }
    
    public void updateEstadoIniciativa() {
        try {
            serviciosBancoIniciativas.updateInitiativeStatus(selectedIniciativa.getId(),Integer.parseInt(idEstado));            
        } catch (ExceptionServiciosBancoIniciativas ex) {
            System.out.println(ex.getMessage());
        } catch (NumberFormatException ex) {
            System.out.println("Lo ingresado no es un id del estado de la iniciativa");
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
    
    public List<Initiative> getIniciativasPalabrasClave() {
        List<Initiative> iniciativas = new ArrayList<>();
        try {
            iniciativas = serviciosBancoIniciativas.consultInitiativeForKeyWord(palabrasClave);
        } catch (ExceptionServiciosBancoIniciativas ex) {
            System.out.println(ex.getMessage());
        } catch (NullPointerException e) {
            System.out.println("Se deben ingresar palabras clave");
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
            System.out.println("Se debe seleccionar una iniciativa");
        }        
    }
    
    public String getIdEstado() {
        return idEstado;
    }
    
    public void setIdEstado(String idEstado) {
        this.idEstado = idEstado;
    }
    
    public String getPalabrasClave() {
        return palabrasClave;
    }
    
    public void setPalabrasClave(String palabrasClave) {
        this.palabrasClave = palabrasClave;
    }
    /*#################################### ESTADISTICAS ####################################*/
    @PostConstruct
    public void init() {
        super.init();
        createPieModel();        
    }
    
    private void createPieModel() {
        pieModel = new PieChartModel();
        HashMap<String, Integer> estadisticaXDependencias = calcularEstadisticasDependencias();
        for (Map.Entry<String, Integer> dependencia : estadisticaXDependencias.entrySet()) {
            pieModel.set(dependencia.getKey(), dependencia.getValue());            
        }

        pieModel.setTitle("Simple Pie");
        pieModel.setLegendPosition("w");
        pieModel.setShadow(false);
    }    
    
    public void itemSelect(ItemSelectEvent event) {
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Item selected",
                "Item Index: " + event.getItemIndex() + ", Series Index:" + event.getSeriesIndex()); 
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public HashMap<String, Integer> calcularEstadisticasDependencias() {
        List<Initiative> iniciativas = this.getIniciativas();
        System.out.println(iniciativas);
        HashMap<String, Integer> estadisticaXDependencias = new HashMap<String, Integer>();
        String dependencia = "";
        int cantidad = 0;
        for (Initiative i : iniciativas){
            dependencia = i.getUser().getArea().getName();
            if(estadisticaXDependencias.containsKey(dependencia)){
                cantidad = estadisticaXDependencias.get(dependencia)+1;
                estadisticaXDependencias.replace(dependencia, cantidad);
            }
            else{
                estadisticaXDependencias.put(dependencia, 1);
            }
        }  
        return estadisticaXDependencias;
    }
    
    public PieChartModel getPieModel() {
        return pieModel;
    }
}