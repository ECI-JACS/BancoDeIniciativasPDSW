package edu.eci.pdsw.sampleprj.dao.mybatis;

import com.google.inject.Inject;

import edu.eci.pdsw.sampleprj.dao.InitiativeDAO;
import edu.eci.pdsw.sampleprj.dao.mybatis.mappers.InitiativeMapper;
import edu.eci.pdsw.samples.entities.Comment;
import edu.eci.pdsw.samples.entities.Initiative;
import edu.eci.pdsw.samples.entities.InitiativeStatus;
import java.sql.Date;
import java.time.LocalDate;

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
    public List<Initiative> loadInitiativeForSearch(String palabrasClave, String proponente, Date fechaPropuesta, int dependencia, int estado) throws PersistenceException {
        try {
            return initiativeMapper.consultarIniciativasPorBusqueda(palabrasClave, proponente, fechaPropuesta, dependencia, estado);
        } catch (PersistenceException e) {
            throw new PersistenceException("Error al consultar iniciativa por búsqueda específica", e);
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
    
    @Override
    public List<Initiative> loadInitiativesUser(String email) throws PersistenceException {
        try {
            return initiativeMapper.consultarIniciativasUsuario(email);
        } catch (PersistenceException e) {
            throw new PersistenceException("Error al consultar las iniciativas del usuario " + email, e);
        }
    }
    
    @Override
    public int loadCommentId() throws PersistenceException {
        try {
            return initiativeMapper.consultarIdComentarios();
        } catch (PersistenceException e) {
            throw new PersistenceException("Error al consultar el máximo id que tomará un comentario", e);
        }
    }

    @Override
    public void updateInitiative(int id, String descripcion, String detalle, String palabrasClave) throws PersistenceException {
        try {            
            initiativeMapper.updateInitiative(id, descripcion, detalle, palabrasClave);
        } catch (PersistenceException e) {
            throw new PersistenceException("Error al modifciar el estado de la iniciativa " + id, e);
        }
    }
}
    
