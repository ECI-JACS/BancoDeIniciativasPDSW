package edu.eci.pdsw.sampleprj.dao.mybatis;

import com.google.inject.Inject;

import edu.eci.pdsw.sampleprj.dao.InitiativeDAO;
import edu.eci.pdsw.sampleprj.dao.mybatis.mappers.InitiativeMapper;
import edu.eci.pdsw.samples.entities.Initiative;
import edu.eci.pdsw.samples.entities.InitiativeStatus;

import org.apache.ibatis.exceptions.PersistenceException;

public class MyBATISInitiativeDAO implements InitiativeDAO {
    
    @Inject
    private InitiativeMapper initiativeMapper;
    
    @Override
    public void saveInitiative(Initiative ini) throws PersistenceException {
        try {
            initiativeMapper.insertarIniciativa(ini);
        } catch (PersistenceException e) {
            throw new PersistenceException("Error al insertar la iniciativa " + ini.toString(), e);
        }
    }

    @Override
    public Initiative loadInitiative(int id) throws PersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void saveInitiativeStatus(InitiativeStatus iniSta) throws PersistenceException {
        try {
            initiativeMapper.insertarEstadoIniciativa(iniSta);
        } catch (PersistenceException e) {
            throw new PersistenceException("Error al insertar el estado " + iniSta.toString() + " de las iniciativas", e);
        }
    }

    @Override
    public InitiativeStatus loadInitiativesStatus(int id) throws PersistenceException {
        try {
            return initiativeMapper.consultarEstadoIniciativas(id);
        } catch (PersistenceException e) {
            throw new PersistenceException("Error al consultar el estado " + id + " de las iniciativas", e);
        }
    }
}