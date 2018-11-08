package edu.eci.pdsw.sampleprj.dao.mybatis;

import com.google.inject.Inject;

import edu.eci.pdsw.sampleprj.dao.InitiativeDAO;
import edu.eci.pdsw.sampleprj.dao.mybatis.mappers.InitiativeMapper;
import edu.eci.pdsw.samples.entities.Initiative;
import edu.eci.pdsw.samples.entities.InitiativeStatus;

import java.util.List;

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
        try {
            return initiativeMapper.consultarIniciativa(id);
        } catch (PersistenceException e) {
            throw new PersistenceException("Error al consultar la iniciativa " + id, e);
        }
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

    @Override
    public void updateInitiativeStatus(int id, int iniStat) throws PersistenceException {
        try {
            initiativeMapper.updateInitiativeStatus(id, iniStat);
        } catch (PersistenceException e) {
            throw new PersistenceException("Error al modifciar el estado de la iniciativa " + id, e);
        }
    }

    @Override
    public List<Initiative> consultInitiativeForKeyWord(String keyWords) throws PersistenceException {
        try {
            return initiativeMapper.consultInitiativeForKeyWord(keyWords);
        } catch (PersistenceException e) {
            throw new PersistenceException("Error al consultar iniciativa por las palabras claves" + keyWords, e);
        }
    }

    @Override
    public int loadInitiativeId() throws PersistenceException {
        try {
            return initiativeMapper.consultarIdIniciativa();
        } catch (PersistenceException e) {
            throw new PersistenceException("Error al consultar el máximo id que tomará una iniciativa", e);
        }
    }

    @Override
    public List<Initiative> loadInitiatives() throws PersistenceException {
        try {
            return initiativeMapper.consultarIniciativas();
        } catch (PersistenceException e) {
            throw new PersistenceException("Error al consultar todas las iniciativas", e);
        }
    }

    @Override
    public List<InitiativeStatus> loadAllInitiativesStatus() throws PersistenceException {
        try {
            return initiativeMapper.consultarEstadosIniciativas();
        } catch (PersistenceException e) {
            throw new PersistenceException("Error al consultar todos los estados de las iniciativas", e);
        }
    }

}
