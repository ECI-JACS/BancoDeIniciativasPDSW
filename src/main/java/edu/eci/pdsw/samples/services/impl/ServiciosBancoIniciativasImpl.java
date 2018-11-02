/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.pdsw.samples.services.impl;

import com.google.inject.Inject;
import edu.eci.pdsw.samples.entities.Iniciative;

import edu.eci.pdsw.samples.services.ExcepcionServiciosBancoIniciativas;
import edu.eci.pdsw.samples.services.ServiciosBancoIniciativas;
import java.util.List;

import java.util.Set;

/**
 *
 * @author hcadavid
 */
public class ServiciosBancoIniciativasImpl implements ServiciosBancoIniciativas {

    @Override
    public void registrarIniciativa(Iniciative ini) throws ExcepcionServiciosBancoIniciativas {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Iniciative consultarIniciativa(int id) throws ExcepcionServiciosBancoIniciativas {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Iniciative> consultarIniciativas(int id) throws ExcepcionServiciosBancoIniciativas {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
