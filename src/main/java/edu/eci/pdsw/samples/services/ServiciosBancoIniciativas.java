package edu.eci.pdsw.samples.services;

import edu.eci.pdsw.samples.entities.Area;
import edu.eci.pdsw.samples.entities.Initiative;
import edu.eci.pdsw.samples.entities.InitiativeStatus;
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
     * Permite modificar eñ estado de una iniciativa
     * @param id
     * @throws ExceptionServiciosBancoIniciativas 
     */
    public void updateInitiativeStatus(int id, InitiativeStatus iniStat) throws ExceptionServiciosBancoIniciativas;
    
    
}
