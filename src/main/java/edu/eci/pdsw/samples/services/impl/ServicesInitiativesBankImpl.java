/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.pdsw.samples.services.impl;

import com.google.inject.Inject;
import edu.eci.pdsw.samples.entities.Initiative;

import edu.eci.pdsw.samples.services.ExceptionServicesInitiativesBank;
import edu.eci.pdsw.samples.services.ServicesInitiativesBank;
import java.util.List;

import java.util.Set;

/**
 *
 * @author hcadavid
 */
public class ServicesInitiativesBankImpl implements ServicesInitiativesBank {

    @Override
    public void registrarIniciativa(Initiative ini) throws ExceptionServicesInitiativesBank {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Initiative consultarIniciativa(int id) throws ExceptionServicesInitiativesBank {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Initiative> consultarIniciativas(int id) throws ExceptionServicesInitiativesBank {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
