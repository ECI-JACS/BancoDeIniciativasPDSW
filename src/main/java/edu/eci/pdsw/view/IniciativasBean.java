package edu.eci.pdsw.view;

import com.google.inject.Inject;
import edu.eci.pdsw.samples.entities.Area;
import edu.eci.pdsw.samples.entities.Initiative;
import edu.eci.pdsw.samples.entities.InitiativeStatus;
import edu.eci.pdsw.samples.entities.User;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import edu.eci.pdsw.samples.services.ExceptionServiciosBancoIniciativas;
import edu.eci.pdsw.samples.services.ServiciosBancoIniciativas;
import edu.eci.pdsw.samples.services.utilities.LoginSession;
import java.io.IOException;
import java.util.Date;
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
    private List<SelectItem> estados;
    private String proponente;
    private Date fechaPropuesta;
    private String dependencia;
    private String palabrasClave;
    private String palabra;
    private PieChartModel pieModel;
    private List<SelectItem> listaAreas;

    @PostConstruct
    public void init() {
        super.init();
        selectedIniciativa = new Initiative();
        idEstado = "";
        estados = new ArrayList<>();
        palabrasClave = "";
        palabra = "";
        proponente = "";
        fechaPropuesta = null;
        dependencia = "";
        listaAreas = new ArrayList<>();
        createPieModel();
    }

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
            serviciosBancoIniciativas.registrarIniciativa(new Initiative(id, description, detail, new java.sql.Date(Calendar.getInstance().getTime().getTime()), null, kewWords, usuario, new InitiativeStatus(1, "En espera de revisión")));
        } catch (ExceptionServiciosBancoIniciativas ex) {
            System.out.println(ex.getMessage());
        }
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro Exitoso", "--Iniciativa creada correctamente--"));
    }

    public void updateEstadoIniciativa() {
        try {
            serviciosBancoIniciativas.updateInitiativeStatus(selectedIniciativa.getId(), Integer.parseInt(idEstado));
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

    public List<Initiative> getIniciativasPorBusqueda() {
        List<Initiative> iniciativas = new ArrayList<>();
        try {
            java.sql.Date fechaSQL = null;
            if (fechaPropuesta != null) {
                fechaSQL = new java.sql.Date(fechaPropuesta.getTime());
            }
            int idE = 0;
            if (!idEstado.equals("")) {
                idE = Integer.parseInt(idEstado);
            }
            int idD = 0;
            if (!dependencia.equals("")) {
                idD = Integer.parseInt(dependencia);
            }
            iniciativas = serviciosBancoIniciativas.consultarIniciativasPorBusqueda(palabrasClave, proponente, fechaSQL, idD, idE);
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
            for (InitiativeStatus is : estadosBD) {
                SelectItem selecItem = new SelectItem(is.getId(), is.getDescription());
                estados.add(selecItem);
            }
        } catch (ExceptionServiciosBancoIniciativas ex) {
            System.out.println(ex.getMessage());
        }
        return estados;
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
    
    /*#################################### ESTADISTICAS ####################################*/
    private void createPieModel() {
        pieModel = new PieChartModel();
        HashMap<String, Integer> estadisticaXDependencias = calcularEstadisticasDependencias();
        for (Map.Entry<String, Integer> dependenciaArea : estadisticaXDependencias.entrySet()) {
            pieModel.set(dependenciaArea.getKey(), dependenciaArea.getValue());
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
        HashMap<String, Integer> estadisticaXDependencias = new HashMap<>();
        String dependenciaArea = "";
        int cantidad = 0;
        for (Initiative i : iniciativas) {
            dependenciaArea = i.getUser().getArea().getName();
            if (estadisticaXDependencias.containsKey(dependenciaArea)) {
                cantidad = estadisticaXDependencias.get(dependenciaArea) + 1;
                estadisticaXDependencias.replace(dependenciaArea, cantidad);
            } else {
                estadisticaXDependencias.put(dependenciaArea, 1);
            }
        }
        return estadisticaXDependencias;
    }

    public Initiative getSelectedIniciativa() {
        return this.selectedIniciativa;
    }

    public void setSelectedIniciativa(Initiative selectedIniciativa) {
        this.selectedIniciativa = selectedIniciativa;
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
    
    public String getPalabra() {
        return palabra;
    }

    public void setPalabra(String palabra) {
        this.palabra = palabra;
        if (this.palabrasClave.equals("")){
            this.palabrasClave = palabra;
        }
        else {
            this.palabrasClave = this.palabrasClave + "," + palabra;
        }
    }

    public String getProponente() {
        return proponente;
    }

    public void setProponente(String proponente) {
        this.proponente = proponente;
    }

    public Date getFechaPropuesta() {
        return fechaPropuesta;
    }

    public void setFechaPropuesta(Date fechaPropuesta) {
        this.fechaPropuesta = fechaPropuesta;
    }

    public String getDependencia() {
        return dependencia;
    }

    public void setDependencia(String dependencia) {
        this.dependencia = dependencia;
    }

    public PieChartModel getPieModel() {
        return pieModel;
    }
    
}
