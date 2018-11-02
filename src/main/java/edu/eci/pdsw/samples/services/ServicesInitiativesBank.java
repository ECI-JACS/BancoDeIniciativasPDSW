package edu.eci.pdsw.samples.services;

import java.sql.Date;
import java.util.List;
import java.util.Set;
import edu.eci.pdsw.samples.entities.Initiative;

/**
 *
 * @author ECI_FACS
 */
public interface ServicesInitiativesBank {

    public void registrarIniciativa(Initiative ini) throws ExceptionServicesInitiativesBank;
    
    public Initiative consultarIniciativa(int id) throws ExceptionServicesInitiativesBank;
    
    public List<Initiative> consultarIniciativas(int id) throws ExceptionServicesInitiativesBank;

}
