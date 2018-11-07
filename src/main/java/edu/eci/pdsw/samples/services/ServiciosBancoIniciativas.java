package edu.eci.pdsw.samples.services;

import java.util.List;
import edu.eci.pdsw.samples.entities.Area;
import edu.eci.pdsw.samples.entities.Initiative;
import edu.eci.pdsw.samples.entities.InitiativeStatus;
import edu.eci.pdsw.samples.entities.Role;
import edu.eci.pdsw.samples.entities.User;

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
     * @param role
     * @return
     * @throws ExceptionServiciosBancoIniciativas 
     */
    public List<User> consultarUsuariosRol(Role role) throws ExceptionServiciosBancoIniciativas;
    
    /**
     * Permite actualizar el rol de un usuario, dado el rol.
     * @param role
     * @throws ExceptionServiciosBancoIniciativas 
     */
    public void actualizarRolUsuario(String email, Role role) throws ExceptionServiciosBancoIniciativas;
    
    /**
     * Permite registrar una iniciativa
     * @param ini
     * @throws ExceptionServiciosBancoIniciativas 
     */
    public void registrarIniciativa(Initiative ini) throws ExceptionServiciosBancoIniciativas;
    
    /**
     * Permite consultar una iniciativa
     * @param id
     * @return iniciativa
     * @throws ExceptionServiciosBancoIniciativas 
     */
    public Initiative consultarIniciativa(int id) throws ExceptionServiciosBancoIniciativas;
    
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
     * Permite modificar el estado de una iniciativa
     * @param id
     * @throws ExceptionServiciosBancoIniciativas 
     */
    public void updateInitiativeStatus(int id,int iniStat) throws ExceptionServiciosBancoIniciativas;
    
    /**
     * Consulta todas las iniciativas asociadas a una palabra clave insertada por el usuario.
     * @param keyWord
     * @return List<Initiative> 
     * @throws ExceptionServiciosBancoIniciativas
     */
    public List<Initiative> consultInitiativeForKeyWord(String keyWord) throws ExceptionServiciosBancoIniciativas;
    
    
}
