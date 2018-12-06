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
import java.util.Arrays;
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
import org.apache.poi.ss.usermodel.FillPatternType;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.FlowEvent;
import org.primefaces.event.RowEditEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

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
    private Initiative selectedMiIniciativa;
    private String idEstado;
    private List<SelectItem> estados;
    private List<Initiative> iniciativas;
    private String proponente;
    private Date fechaPropuesta;
    private String dependencia;
    private List<SelectItem> listaAreas;
    private List<String> meses;
    private PieChartModel pieModelDep;
    private PieChartModel pieModelFecha;
    private PieChartModel pieModelEstado;
    private String buttonEstdisticaFecha;
    private boolean buscando;
    private HashMap<String, Integer> estadisticaXDependencias;
    private HashMap<String, Integer> estadisticaXFecha;
    private HashMap<String, Integer> estadisticaXEstado;
    private ExcelOptions excelOpt;
    private List<String> listaPalabrasClave;
    private List<String> listaPalabrasClaveConsulta;
    private String selectedPalabra;
    private String selectedPalabraConsulta;
    private User usuarioProponente;
    private boolean registrar;
    private List<Initiative> iniciativasRelacionadas;
    private int intentosRegistrar;
    private String descripcionMiIniciativa;
    private String detalleMiIniciativa;
    private Initiative selectedIniciativaRelacionada;

    @PostConstruct
    public void init() {
        super.init();
        try {
            iniciativas = serviciosBancoIniciativas.consultarIniciativas();
        } catch (ExceptionServiciosBancoIniciativas ex) {
            Logger.getLogger(IniciativasBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        inicializarVariables();
        createPieModelDependencias();
    }

    public void inicializarVariables() {
        this.selectedIniciativa = new Initiative();
        this.selectedMiIniciativa = new Initiative();
        this.idEstado = "";
        this.estados = new ArrayList<>();
        this.proponente = "";
        this.fechaPropuesta = null;
        this.dependencia = "";
        this.listaAreas = new ArrayList<>();
        this.buscando = true;
        this.iniciativas = new ArrayList<>();
        this.listaPalabrasClave = new ArrayList<>();
        this.listaPalabrasClaveConsulta = new ArrayList<>();
        this.usuarioProponente = new User();
        this.registrar = true;
        this.iniciativasRelacionadas = new ArrayList<>();
        this.intentosRegistrar = 0;
        this.descripcionMiIniciativa = "";
        this.detalleMiIniciativa = "";
        this.selectedIniciativaRelacionada = new Initiative();
        this.meses = Arrays.asList("Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre");
        this.buttonEstdisticaFecha = "Año";
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
            String palabrasClaveR = convertirPalabrasClaveEnString();
            this.verificarIniciativasRelacionadas(palabrasClaveR);
            if (!this.iniciativasRelacionadas.isEmpty() && intentosRegistrar < 1) {
                this.registrar = false;
                intentosRegistrar++;
                throw new ExceptionServiciosBancoIniciativas("Hay palabras relacionadas");
            }
            serviciosBancoIniciativas.registrarIniciativa(new Initiative(id, description, detail, new java.sql.Date(Calendar.getInstance().getTime().getTime()), null, palabrasClaveR, usuario, new InitiativeStatus(1, "En espera de revisión")));
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro Exitoso", "--Iniciativa creada correctamente--"));
            inicializarVariables();
        } catch (ExceptionServiciosBancoIniciativas ex) {
            if (ex.getMessage().equals("Hay palabras relacionadas")) {
                System.out.println(ex.getMessage());
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Verifique", "--Iniciativa no creada--"));
            }
        } catch (NullPointerException ex) {
            System.out.println("No se ha seleccionado una iniciativa para ver sus relacionadas");
        }
    }

    public String convertirPalabrasClaveEnString() {
        String palabrasClaveR = "";
        for (String palabra : listaPalabrasClave) {
            if (palabrasClaveR.isEmpty()) {
                palabrasClaveR = palabra;
            } else {
                palabrasClaveR = palabrasClaveR + "," + palabra;
            }
        }
        return palabrasClaveR;
    }

    /**
     * Este método permite verificar qué iniciativas están relacionadas, dadas
     * unas palabras clave. Si el porcentaje de similitud es mayor o igual a
     * 60%, entonces se puede considerar una iniciativa relacionada.
     *
     * @param palabrasClave
     * @throws ExceptionServiciosBancoIniciativas
     */
    public void verificarIniciativasRelacionadas(String palabrasClave) throws ExceptionServiciosBancoIniciativas {
        List<Initiative> iniciativasPosiblementeRelacionadas = serviciosBancoIniciativas.consultarIniciativasPorBusqueda(palabrasClave, "", null, 0, 0);
        List<Initiative> iniciativasRelacionadasPre = new ArrayList<>();
        String[] palabrasClaveVerificar = palabrasClave.split(",");
        String[] palabrasClaveIniciativa;
        double porcentajeSimilitud = 0;
        int palabrasIguales = 0;
        for (Initiative i : iniciativasPosiblementeRelacionadas) {
            if (i.getId() != selectedIniciativaRelacionada.getId()) {
                palabrasClaveIniciativa = i.getKeyWords().split(",");
                for (String p1 : palabrasClaveVerificar) {
                    for (String p2 : palabrasClaveIniciativa) {
                        if (p1.equals(p2)) {
                            palabrasIguales++;
                            break;
                        }
                    }
                }
                porcentajeSimilitud = palabrasIguales * 100 / palabrasClaveIniciativa.length;
                if (porcentajeSimilitud >= 60) {
                    iniciativasRelacionadasPre.add(i);
                }
            }
        }
        this.iniciativasRelacionadas = iniciativasRelacionadasPre;
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

    public void busqueda() {
        this.buscando = true;
    }

    /**
     * Obtine las iniciativas ya calculadas. No obtienes las iniciativas desde
     * servicios, porque para poder realizar el ordenamiento por columna, se
     * debe consultar las iniciativas que ya se habían consultado. Si uno genera
     * la consutla desde serivicos no se puede usar esa funcion de primefaes. El
     * método que trae las iniciativas desde servicios se llama
     * ObtenerIniciativasDeServicios Para solucionar el ordenamiento por
     * columna, se hace uso de un booleano(buscando): cuando se desea consultar
     * por algun campo en especifico, al momento de escribir se llama al metodo
     * getIniciativasPorBusqueda() y se manda desde el xhtml el booleano
     * buscando(como true) para indicar que el metodo que se va a usar es
     * ObtenerIniciativasDeServicios, en este metodo(getIniciativasPorBusqueda),
     * al final se asigna false al booleano, para que si se desea ordenar en la
     * tabla se llame al metodo getIniciativas.
     *
     *
     * @return
     */
    public List<Initiative> obtenerIniciativasDeServicios() {
        List<Initiative> iniciativas = new ArrayList<>();
        try {
            iniciativas = serviciosBancoIniciativas.consultarIniciativas();

        } catch (ExceptionServiciosBancoIniciativas ex) {
            Logger.getLogger(IniciativasBean.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return iniciativas;
    }

    public List<Initiative> iniciativasUsuario() {
        List<Initiative> iniciativasUsuario = new ArrayList<>();
        HttpSession hs;
        try {
            hs = LoginSession.getSession();
            User usuario = (User) hs.getAttribute("usuario");
            iniciativasUsuario = serviciosBancoIniciativas.consultarIniciativasUsuario(usuario.getEmail());
        } catch (ExceptionServiciosBancoIniciativas ex) {
            System.out.println(ex.getMessage());
        }
        return iniciativasUsuario;
    }

    public List<Initiative> getIniciativas() {
        return iniciativas;
    }

    public void setIniciativas(List<Initiative> iniciativas) {
        this.iniciativas = iniciativas;
    }

    public List<Initiative> getIniciativasPorBusqueda() {
        if (buscando) {
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
                String palabrasClaveR = "";
                if (!listaPalabrasClaveConsulta.isEmpty()) {
                    for (String palabra : listaPalabrasClaveConsulta) {
                        if (palabrasClaveR.isEmpty()) {
                            palabrasClaveR = palabra;
                        } else {
                            palabrasClaveR = palabrasClaveR + "," + palabra;
                        }
                    }
                }
                iniciativas = serviciosBancoIniciativas.consultarIniciativasPorBusqueda(palabrasClaveR, proponente, fechaSQL, idD, idE);
            } catch (ExceptionServiciosBancoIniciativas ex) {
                System.out.println(ex.getMessage());
            }
            buscando = false;
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
    private void createPieModelDependencias() {
        pieModelDep = new PieChartModel();
        HashMap<String, Integer> estadisticaXDependencias = calcularEstadisticasDependencias();
        for (Map.Entry<String, Integer> dependenciaArea : estadisticaXDependencias.entrySet()) {
            //System.out.println("##################################: "+calcularPorcentaje(dependenciaArea.getKey()));
            pieModelDep.set(dependenciaArea.getKey(), dependenciaArea.getValue());
        }
        pieModelDep.setLegendPosition("w");
        pieModelDep.setShadow(false);
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

    private void createPieModelFecha() {
        pieModelFecha = new PieChartModel();
        if (buttonEstdisticaFecha.equals("Mes")) {
            HashMap<Integer, Integer> estadisticaXFecha = calcularEstadisticasAño();
            for (Map.Entry<Integer, Integer> año : estadisticaXFecha.entrySet()) {
                //System.out.println("##################################: "+calcularPorcentaje(dependenciaArea.getKey()));
                pieModelFecha.set(año.getKey().toString(), año.getValue());
            }
            pieModelFecha.setLegendPosition("w");
            pieModelFecha.setShadow(false);
        } else {
            HashMap<String, Integer> estadisticaXFecha = calcularEstadisticasMes();
            for (Map.Entry<String, Integer> mes : estadisticaXFecha.entrySet()) {
                //System.out.println("##################################: "+calcularPorcentaje(dependenciaArea.getKey()));
                pieModelFecha.set(mes.getKey(), mes.getValue());
            }
            pieModelFecha.setLegendPosition("w");
            pieModelFecha.setShadow(false);
        }
    }

    public HashMap calcularEstadisticasFecha() {
        if (buttonEstdisticaFecha.equals("Mes")) {
            return calcularEstadisticasAño();
        } else {
            return calcularEstadisticasMes();
        }
    }

    public HashMap<String, Integer> calcularEstadisticasMes() {
        List<Initiative> iniciativas;
        estadisticaXFecha = new HashMap<>();
        iniciativas = obtenerIniciativasDeServicios();
        String mes;
        int cantidad = 0;
        for (Initiative i : iniciativas) {
            mes = meses.get((i.getCreationDate().getMonth()));
            if (estadisticaXFecha.containsKey(mes)) {
                cantidad = estadisticaXFecha.get(mes) + 1;
            } else {
                cantidad = 1;
            }
            estadisticaXFecha.put(mes, cantidad);
        }
        return estadisticaXFecha;
    }

    public HashMap<Integer, Integer> calcularEstadisticasAño() {
        List<Initiative> iniciativas;
        HashMap<Integer, Integer> estadisticaXAño = new HashMap<>();
        iniciativas = obtenerIniciativasDeServicios();
        int año;
        int cantidad = 0;
        for (Initiative i : iniciativas) {
            año = i.getCreationDate().getYear() + 1900;
            if (estadisticaXAño.containsKey(año)) {
                cantidad = estadisticaXAño.get(año) + 1;
            } else {
                cantidad = 1;
            }
            estadisticaXAño.put(año, cantidad);
        }
        return estadisticaXAño;
    }
    
    private void createPieModelEstado() {
        pieModelEstado = new PieChartModel();
        HashMap<String, Integer> estadisticaXEstado = calcularEstadisticasEstado();
        for (Map.Entry<String, Integer> estado : estadisticaXEstado.entrySet()) {
            //System.out.println("##################################: "+calcularPorcentaje(dependenciaArea.getKey()));
            pieModelEstado.set(estado.getKey(), estado.getValue());
        }
        pieModelEstado.setLegendPosition("w");
        pieModelEstado.setShadow(false);
    }
    
    public HashMap<String, Integer> calcularEstadisticasEstado() {
        List<Initiative> iniciativas;
        estadisticaXEstado = new HashMap<>();
        iniciativas = obtenerIniciativasDeServicios();
        String estado;
        int cantidad = 0;
        for (Initiative i : iniciativas) {
            estado = i.getIniciativeStatus().getDescription();
            if (estadisticaXEstado.containsKey(estado)) {
                cantidad = estadisticaXEstado.get(estado) + 1;
            } else {
                cantidad = 1;
            }
            estadisticaXEstado.put(estado, cantidad);
        }
        return estadisticaXEstado;
    }    
    

    public List<String> dependenciasGrafica() {
        List<String> dependencias = new ArrayList<>();
        HashMap<String, Integer> estadisticaXDependencias = calcularEstadisticasDependencias();
        for (Map.Entry<String, Integer> dependenciaArea : estadisticaXDependencias.entrySet()) {
            dependencias.add(dependenciaArea.getKey());
        }
        return dependencias;
    }

    /**
     * Permite calcular el porcentaje para hacer las gráficas
     *
     * @param area
     * @return
     */
    /*private String calcularPorcentaje(String area) {
        List<Initiative> iniciativas = obtenerIniciativasDeServicios();
        int total = iniciativas.size();
        double porcentaje = ((estadisticaXDependencias.get(area)) * 100) / total;

        return Double.toString(porcentaje);
    }*/
    /**
     * Metodo para exportar reporte en excel
     *
     * @param document
     */
    public void postProcessXLS(Object document) {
        HSSFWorkbook wb = (HSSFWorkbook) document;
        HSSFSheet sheet = wb.getSheetAt(0);
        HSSFRow header = sheet.getRow(0);

        HSSFCellStyle cellStyle = wb.createCellStyle();
        Color color = Color.GREEN;

        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        for (int i = 0; i < header.getPhysicalNumberOfCells(); i++) {
            HSSFCell cell = header.getCell(i);

            cell.setCellStyle(cellStyle);
        }
    }

    /*############################## Editar Mis Iniciativas ########################################################*/
    public boolean permisoParaEditarIniciativa() throws IOException {
        boolean permiso = false;
        try {
            System.out.println("Entrooooooooooooooo");
            permiso = selectedMiIniciativa.getIniciativeStatus().getDescription().equals("En espera de revisión");
        } catch (NullPointerException ex) {
            return false;
        }
        return permiso;
    }

    public boolean mostrarBotonAgregar(String palabraI) {
        boolean permiso = false;
        if (!palabraI.equals("")) {
            permiso = true;
        }
        return permiso;
    }

    public void cargarPalabrasClaveEnTabla() {
        listaPalabrasClave.clear();
        List<String> palabras = new ArrayList<String>(Arrays.asList(selectedMiIniciativa.showKeyWords().split(", ")));
        for (String a : palabras) {
            listaPalabrasClave.add(a);
            System.out.println("PALABRAS INICIALES A : " + a);
        }
        System.out.println("PALABRAS INICIALESSSSSSS: " + listaPalabrasClave);
    }

    public void actualizarIniciativa(String descripcion, String detalle) {
        try {
            System.out.println("Voy a actualizar");
            System.out.println("id=" + selectedMiIniciativa.getId() + ",description=" + descripcion + ",detalle=" + detalle);
            serviciosBancoIniciativas.actualizarIniciativa(this.selectedMiIniciativa.getId(), descripcion, detalle, convertirPalabrasClaveEnString());
        } catch (ExceptionServiciosBancoIniciativas ex) {
            Logger.getLogger(IniciativasBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void onRowSelect(SelectEvent event) {
        FacesMessage msg = new FacesMessage("Iniciativa Selectd", Integer.toString(((Initiative) event.getObject()).getId()));
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onRowEdit(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Iniciativa Editada", Integer.toString(((Initiative) event.getObject()).getId()));
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Edición Cancelada", Integer.toString(((Initiative) event.getObject()).getId()));
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onCellEdit(CellEditEvent event) {
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();

        if (newValue != null && !newValue.equals(oldValue)) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed", "Old: " + oldValue + ", New:" + newValue);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

    /*################################# GETTERS AND SETTERS ################################*/
 /*######################################################################################*/

 /*################################# GETTERS AND SETTERS ################################*/
    public Initiative getSelectedIniciativa() {
        System.out.println("2################## Se guardo esta iniciativa: " + selectedMiIniciativa.getId());
        return this.selectedIniciativa;
    }

    public void setSelectedIniciativa(Initiative selectedIniciativa) {
        this.selectedIniciativa = selectedIniciativa;
    }

    public Initiative getSelectedMiIniciativa() {
        return this.selectedMiIniciativa;
    }

    public String getDescripcionMiIniciativa() {
        return descripcionMiIniciativa;
    }

    public String getDetalleMiIniciativa() {
        return detalleMiIniciativa;
    }

    public void setDescripcionMiIniciativa(String descripcionMiIniciativa) {
        this.descripcionMiIniciativa = descripcionMiIniciativa;
    }

    public void setDetalleMiIniciativa(String detalleMiIniciativa) {
        this.detalleMiIniciativa = detalleMiIniciativa;
    }

    public void setSelectedMiIniciativa(Initiative selectedMiIniciativa) {
        System.out.println("################## Set MI INICIATIVA: " + selectedMiIniciativa.getId());
        System.out.println("################## Set MI INICIATIVA: " + selectedMiIniciativa.getDetail());
        this.selectedMiIniciativa = selectedMiIniciativa;
        this.descripcionMiIniciativa = selectedMiIniciativa.getDescription();
        this.detalleMiIniciativa = selectedMiIniciativa.getDetail();
    }

    public String getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(String idEstado) {
        this.idEstado = idEstado;
    }

    public void agregarPalabra(String palabraI) {
        System.out.println("1 ################### agregar : " + listaPalabrasClave.toString());
        listaPalabrasClave.add(palabraI);
        System.out.println("2 ################### agregar palabra: " + palabraI);
        System.out.println("3 ################### agregar Lista: " + listaPalabrasClave.toString());
    }

    public void agregarPalabraConsulta(String palabraI) {
        listaPalabrasClaveConsulta.add(palabraI);
    }

    public void eliminarPalabras() {
        System.out.println("1 ################### eliminar: " + listaPalabrasClave.toString());
        listaPalabrasClave.remove(listaPalabrasClave.indexOf(selectedPalabra));
        System.out.println("2 ################### eliminar Palabra: " + selectedPalabra);
        System.out.println("3 ################### eliminar Lista: " + listaPalabrasClave.toString());
    }

    public void eliminarPalabrasConsulta() {
        listaPalabrasClaveConsulta.remove(listaPalabrasClaveConsulta.indexOf(selectedPalabraConsulta));
    }

    public List<String> getListaPalabrasClave() {
        return listaPalabrasClave;
    }

    public void setListaPalabrasClave(List<String> listaPalabrasClave) {
        this.listaPalabrasClave = listaPalabrasClave;
    }

    public List<String> getListaPalabrasClaveConsulta() {
        return listaPalabrasClaveConsulta;
    }

    public void setListaPalabrasClaveConsulta(List<String> listaPalabrasClaveConsulta) {
        this.listaPalabrasClaveConsulta = listaPalabrasClaveConsulta;
    }

    public String getSelectedPalabra() {
        return selectedPalabra;
    }

    public void setSelectedPalabra(String selectedPalabra) {
        this.selectedPalabra = selectedPalabra;
    }

    public String getSelectedPalabraConsulta() {
        return selectedPalabraConsulta;
    }

    public void setSelectedPalabraConsulta(String selectedPalabraConsulta) {
        this.selectedPalabraConsulta = selectedPalabraConsulta;
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

    public String getValueButtonEstadistica() {
        return buttonEstdisticaFecha;
    }

    public void estadisticas() {
        if (buttonEstdisticaFecha.equals("Mes")) {
            buttonEstdisticaFecha = "Año";
        } else {
            buttonEstdisticaFecha = "Mes";
        }

    }

    public String getValueColumnaTabla() {
        if (buttonEstdisticaFecha.equals("Mes")) {
            return "Año";
        } else {
            return "Mes";
        }
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

    public PieChartModel getPieModelDep() {
        createPieModelDependencias();
        return pieModelDep;
    }

    public PieChartModel getPieModelFecha() {
        createPieModelFecha();
        return pieModelFecha;
    }
    public PieChartModel getPieModelEstado() {
        createPieModelEstado();
        return pieModelEstado;
    }

    public User getUsuarioProponente() {
        return usuarioProponente;
    }

    public void setUsuarioProponente(User usuarioProponente) {
        this.usuarioProponente = usuarioProponente;
    }

    public boolean isRegistrar() {
        return registrar;
    }

    public void setRegistrar(boolean registrar) {
        this.registrar = registrar;
    }

    public List<Initiative> getIniciativasRelacionadas() {
        return iniciativasRelacionadas;
    }

    public void setIniciativasRelacionadas(List<Initiative> iniciativasRelacionadas) {
        this.iniciativasRelacionadas = iniciativasRelacionadas;
    }

    public Initiative getSelectedIniciativaRelacionada() {
        return selectedIniciativaRelacionada;
    }

    public void setSelectedIniciativaRelacionada(Initiative selectedIniciativaRelacionada) {
        this.selectedIniciativaRelacionada = selectedIniciativaRelacionada;
    }

    /*#################################### Wizard ##################################*/

    public String onFlowProcess(FlowEvent event) {
        return event.getNewStep();
    }
}
