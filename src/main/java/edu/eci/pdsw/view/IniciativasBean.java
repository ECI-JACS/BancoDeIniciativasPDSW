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
import java.awt.Color;
import java.io.IOException;
import java.util.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpSession;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.primefaces.component.export.ExcelOptions;
import org.primefaces.model.chart.PieChartModel;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.FillPatternType;

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
    private List<Initiative> iniciativas;    
    private String proponente;
    private Date fechaPropuesta;
    private String dependencia;
    private String palabrasClave;
    private String palabra;
    private String keywords;
    private String keyword;    
    private List<SelectItem> listaAreas;
    private PieChartModel pieModel; 
    private boolean buscando;
    private HashMap<String, Integer> estadisticaXDependencias;
    private ExcelOptions excelOpt;

    @PostConstruct
    public void init() {        
        super.init();     
        try {
            iniciativas = serviciosBancoIniciativas.consultarIniciativas();
        } catch (ExceptionServiciosBancoIniciativas ex) {
            Logger.getLogger(IniciativasBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        inicializarVariables();
        createPieModel();  
    }
    
    public void inicializarVariables() {
        selectedIniciativa = new Initiative();
        idEstado = "";
        estados = new ArrayList<>();
        palabrasClave = "";
        palabra = "";
        keywords = "";
        keyword = "";
        proponente = "";
        fechaPropuesta = null;
        dependencia = "";
        listaAreas = new ArrayList<>();
        iniciativas = new ArrayList<>();
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

    public void registrarIniciativa(String description, String detail) {
        HttpSession hs;
        try {
            hs = LoginSession.getSession();
            User usuario = (User) hs.getAttribute("usuario");
            int id = serviciosBancoIniciativas.consultarIdIniciativa();
            serviciosBancoIniciativas.registrarIniciativa(new Initiative(id, description, detail, new java.sql.Date(Calendar.getInstance().getTime().getTime()), null, keywords, usuario, new InitiativeStatus(1, "En espera de revisión")));
        } catch (ExceptionServiciosBancoIniciativas ex) {
            System.out.println(ex.getMessage());
        }
        inicializarVariables();
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
    
    public void busqueda(){
        this.buscando=true;
    }

    /**
     * Obtine las iniciativas ya calculadas. No obtienes las iniciativas desde servicios, 
     * porque para poder realizar el ordenamiento por columna, se debe consultar las iniciativas
     * que ya se habían consultado. Si uno genera la consutla desde serivicos no se puede usar esa
     * funcion de primefaes. El método que trae las iniciativas desde servicios se llama ObtenerIniciativasDeServicios
     * Para solucionar el ordenamiento por columna, se hace uso de un booleano(buscando): cuando se desea consultar 
     * por algun campo en especifico, al momento de escribir se llama al metodo getIniciativasPorBusqueda()
     * y se manda desde el xhtml el booleano buscando(como true) para indicar que el metodo que se va a usar 
     * es ObtenerIniciativasDeServicios, en este metodo(getIniciativasPorBusqueda), al final se asigna false
     * al booleano, para que si se desea ordenar en la tabla se llame al metodo getIniciativas.     * 
     * @return 
     */
    public List<Initiative> obtenerIniciativasDeServicios() {  
        List<Initiative> iniciativas = new ArrayList<>();
        try {
            iniciativas = serviciosBancoIniciativas.consultarIniciativas();
        } catch (ExceptionServiciosBancoIniciativas ex) {
            Logger.getLogger(IniciativasBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return iniciativas;
    }
    
    public List<Initiative> getIniciativas() {  
        return iniciativas;
    }
    
    public void setIniciativas(List<Initiative> iniciativas) {
        this.iniciativas = iniciativas;
    }

    public List<Initiative> getIniciativasPorBusqueda() {
        if(buscando){
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
            buscando=false;
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
            //System.out.println("##################################: "+calcularPorcentaje(dependenciaArea.getKey()));
            pieModel.set(dependenciaArea.getKey(), dependenciaArea.getValue());
        }
        pieModel.setTitle("Simple Pie");
        pieModel.setLegendPosition("w");
        pieModel.setShadow(false);
    }

    public HashMap<String, Integer> calcularEstadisticasDependencias() {
        List<Initiative> iniciativas;
        estadisticaXDependencias = new HashMap<>();
        iniciativas = obtenerIniciativasDeServicios();
        String dependenciaArea;
        int cantidad = 0;
        for (Initiative i : iniciativas) {
            dependenciaArea = i.getUser().getArea().getName();
            if (estadisticaXDependencias.containsKey(dependenciaArea)) {
                cantidad = estadisticaXDependencias.get(dependenciaArea) + 1;                 
            } else { 
                cantidad = 1;                
            }
            estadisticaXDependencias.put(dependenciaArea, cantidad);
        }       
        return estadisticaXDependencias;
        
    }
    
    private String calcularPorcentaje(String area) {
        List<Initiative> iniciativas = obtenerIniciativasDeServicios();
        int total = iniciativas.size();        
        double porcentaje = ((estadisticaXDependencias.get(area))*100)/total;
        
        return Double.toString(porcentaje);
    }
    /**
     * Metodo para exportar reporte en excel
     * @param document 
     */
     public void postProcessXLS(Object document) {
        HSSFWorkbook wb = (HSSFWorkbook) document;
        HSSFSheet sheet = wb.getSheetAt(0);
        HSSFRow header = sheet.getRow(0);
         
        HSSFCellStyle cellStyle = wb.createCellStyle(); 
        Color color = Color.GREEN;
        
        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
         
        for(int i=0; i < header.getPhysicalNumberOfCells();i++) {
            HSSFCell cell = header.getCell(i);
             
            cell.setCellStyle(cellStyle);
        }
    }
        
    /*################################ GETTERS AND SETTERS ###############################*/
    
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

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
        if (this.keywords.equals("")){
            this.keywords = keyword;
        }
        else {
            this.keywords = this.keywords + "," + keyword;
        }
    }

    public PieChartModel getPieModel() {
        createPieModel();
        return pieModel;
    }
    
}
