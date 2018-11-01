package edu.eci.pdsw.samples.services;

import java.sql.Date;
import java.util.List;
import java.util.Set;
import edu.eci.pdsw.samples.entities.Comentario;

/**
 *
 * @author ECI_FACS
 */
public interface ServiciosBancoIniciativas {

    public void registrarIniciativa(Iniciativa ini) throws ExcepcionServiciosBancoIniciativas;
    
    public Iniciativa consultarIniciativa(int id) throws ExcepcionServiciosBancoIniciativas;
    
    public List<Iniciativa> consultarIniciativas(int id) throws ExcepcionServiciosBancoIniciativas;

}
