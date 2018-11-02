package edu.eci.pdsw.samples.services;

import java.sql.Date;
import java.util.List;
import java.util.Set;
import edu.eci.pdsw.samples.entities.Iniciative;

/**
 *
 * @author ECI_FACS
 */
public interface ServiciosBancoIniciativas {

    public void registrarIniciativa(Iniciative ini) throws ExcepcionServiciosBancoIniciativas;
    
    public Iniciative consultarIniciativa(int id) throws ExcepcionServiciosBancoIniciativas;
    
    public List<Iniciative> consultarIniciativas(int id) throws ExcepcionServiciosBancoIniciativas;

}
