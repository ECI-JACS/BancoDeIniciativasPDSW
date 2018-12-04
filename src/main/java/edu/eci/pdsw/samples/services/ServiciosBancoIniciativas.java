package edu.eci.pdsw.samples.services;

import java.util.List;
import edu.eci.pdsw.samples.entities.Area;
import edu.eci.pdsw.samples.entities.Comment;
import edu.eci.pdsw.samples.entities.Initiative;
import edu.eci.pdsw.samples.entities.InitiativeStatus;
import edu.eci.pdsw.samples.entities.Role;
import edu.eci.pdsw.samples.entities.User;
import java.sql.Date;

/**
 *
 * @author ECI_FACS
 */
public interface ServiciosBancoIniciativas {
    
    /**
     * Permite registrar un nuevo usuario
     * @param user
     * @throws ExceptionServiciosBancoIniciativas 
     */
    public void registrarUsuario(User user) throws ExceptionServiciosBancoIniciativas;
    
    /**
     * Permite consultar a un usuario por su correo
     * @param email
     * @return usuario
     * @throws ExceptionServiciosBancoIniciativas 
     */
    public User consultarUsuario(String email) throws ExceptionServiciosBancoIniciativas;
    
    /**
     * Permite consultar a varios usuarios, dado el rol o pefil
     * @param nombres
     * @param apellidos
     * @param email
     * @param carnet
     * @param role
     * @param idArea
     * @return
     * @throws ExceptionServiciosBancoIniciativas 
     */
    public List<User> consultarUsuariosPorBusqueda(String nombres, String apellidos, String email, int carnet, int idArea, String role) throws ExceptionServiciosBancoIniciativas;
    
    /**
     * Permite actualizar el rol de un usuario, dado el rol.
     * @param email
     * @param role
     * @throws ExceptionServiciosBancoIniciativas 
     */
    public void actualizarRolUsuario(String email, String role) throws ExceptionServiciosBancoIniciativas;
    
    /**
     * Permite consulta el siguiente Id que tendrá una iniciativa
     * @return
     * @throws ExceptionServiciosBancoIniciativas 
     */
    public int consultarIdIniciativa() throws ExceptionServiciosBancoIniciativas;
    
    /**
     * Permite registrar una iniciativa
     * @param ini
     * @throws ExceptionServiciosBancoIniciativas 
     */
    public void registrarIniciativa(Initiative ini) throws ExceptionServiciosBancoIniciativas;
            
    /**
     * Permite actualizar una iniciativa
     * @param id
     * @param descripcion
     * @param detalle
     * @param fechaM
     * @throws ExceptionServiciosBancoIniciativas 
     */
    public void actualizarIniciativa(int id, String descripcion, String detalle, String palabrasClave) throws ExceptionServiciosBancoIniciativas;
    
    /**
     * Permite consultar una iniciativa
     * @param id
     * @return iniciativa
     * @throws ExceptionServiciosBancoIniciativas 
     */
    public Initiative consultarIniciativa(int id) throws ExceptionServiciosBancoIniciativas;
    
    /**
     * Permite consultar todas las iniciativas existentes
     * @return
     * @throws ExceptionServiciosBancoIniciativas 
     */
    public List<Initiative> consultarIniciativas() throws ExceptionServiciosBancoIniciativas;
    
    /**
     * Permite consultar todas las iniciativas existentes de un usuario
     * @return
     * @throws ExceptionServiciosBancoIniciativas 
     */
    public List<Initiative> consultarIniciativasUsuario(String email) throws ExceptionServiciosBancoIniciativas;
    
    /**
     * Permite consultar un área o dependencia en específico
     * @param id
     * @return area
     * @throws ExceptionServiciosBancoIniciativas 
     */
    public Area consultarArea(int id) throws ExceptionServiciosBancoIniciativas;
    
    /**
     * Permite consultar las áreas o dependencias
     * @return Lista de areas
     * @throws ExceptionServiciosBancoIniciativas 
     */
    public List<Area> consultarAreas() throws ExceptionServiciosBancoIniciativas;
    
    /**
     * Permite registrar una área o dependencia
     * @param area
     * @throws ExceptionServiciosBancoIniciativas 
     */
    public void registrarArea(Area area) throws ExceptionServiciosBancoIniciativas;
    
    /**
     * Permite registrar un estado para las iniciativas
     * @param iniSta
     * @throws ExceptionServiciosBancoIniciativas 
     */
    public void registrarEstadoIniciativa(InitiativeStatus iniSta) throws ExceptionServiciosBancoIniciativas;
    
    /**
     * Permite conocer el estado de una iniciativa
     * @param id
     * @return InitiativeStatus
     * @throws ExceptionServiciosBancoIniciativas 
     */
    public InitiativeStatus consultarEstadoIniciativas(int id) throws ExceptionServiciosBancoIniciativas;
    
    /**
     * Permite consultar todos los estados de las iniciativas
     * @return
     * @throws ExceptionServiciosBancoIniciativas 
     */
    public List<InitiativeStatus> consultarEstadosIniciativas() throws ExceptionServiciosBancoIniciativas;
    
    /**
     * Permite modificar el estado de una iniciativa
     * @param id
     * @param iniStat
     * @throws ExceptionServiciosBancoIniciativas 
     */
    public void updateInitiativeStatus(int id,int iniStat) throws ExceptionServiciosBancoIniciativas;
    
    /**
     * Consulta todas las iniciativas asociadas a una búsqueda específica.
     * @param palabrasClave
     * @param proponente
     * @param fechaPropuesta
     * @param dependencia
     * @param estado
     * @return
     * @throws ExceptionServiciosBancoIniciativas 
     */
    public List<Initiative> consultarIniciativasPorBusqueda(String palabrasClave, String proponente, Date fechaPropuesta, int dependencia, int estado) throws ExceptionServiciosBancoIniciativas;
    
    /**
     * Elimina un usuario recibiendo como parámetro su email.
     * @param email
     * @throws ExceptionServiciosBancoIniciativas
     */
    public void deleteUser(String email) throws ExceptionServiciosBancoIniciativas;
    
    /**
     * Inserta un comentario en una iniciativa.
     * @param com
     * @throws ExceptionServiciosBancoIniciativas
     */
    public void insertarComentarioEnUnaIniciativa(Comment com) throws ExceptionServiciosBancoIniciativas;
    
    /**
     * Permite consulta el siguiente Id que tendrá un comentario.
     * @return
     * @throws ExceptionServiciosBancoIniciativas 
     */
    public int consultarIdComentarios() throws ExceptionServiciosBancoIniciativas;
}