/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.pdsw.samples.services.impl;

import com.google.inject.Inject;
import edu.eci.pdsw.samples.entities.Comentario;
import edu.eci.pdsw.samples.persistence.DaoComentario;
import edu.eci.pdsw.samples.persistence.PersistenceException;
import edu.eci.pdsw.samples.services.ExcepcionServiciosSuscripciones;
import edu.eci.pdsw.samples.services.ServiciosSuscripciones;
import java.util.Set;

/**
 *
 * @author hcadavid
 */
public class ServiciosSuscripcionesImpl implements ServiciosSuscripciones {

    @Inject
    private DaoComentario daoc;
    
    @Override
    public Set<Comentario> comenteriosMasBajosPorRangoEdad(int a, int b) throws ExcepcionServiciosSuscripciones {
        try {
            return daoc.loadByScoreAndAge(10, a, b);
        } catch (PersistenceException ex) {
            throw new ExcepcionServiciosSuscripciones("err pers", ex);
        }
    }

    @Override
    public Set<Comentario> comenteriosRegistrados() throws ExcepcionServiciosSuscripciones {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
