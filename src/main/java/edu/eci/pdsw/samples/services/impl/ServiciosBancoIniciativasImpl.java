package edu.eci.pdsw.samples.services.impl;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import edu.eci.pdsw.sampleprj.dao.AreaDAO;
import edu.eci.pdsw.sampleprj.dao.InitiativeDAO;
import edu.eci.pdsw.sampleprj.dao.UserDAO;
import edu.eci.pdsw.samples.entities.Area;
import edu.eci.pdsw.samples.entities.Initiative;
import edu.eci.pdsw.samples.entities.InitiativeStatus;
import edu.eci.pdsw.samples.entities.Role;
import edu.eci.pdsw.samples.entities.User;
import edu.eci.pdsw.samples.services.ExceptionServiciosBancoIniciativas;
import edu.eci.pdsw.samples.services.ServiciosBancoIniciativas;

import java.util.List;

import org.apache.ibatis.exceptions.PersistenceException;

/**
 *
 * @author ECI-FACS
 */
@Singleton
public class ServiciosBancoIniciativasImpl implements ServiciosBancoIniciativas {

    @Inject
    private UserDAO userDAO;

    @Inject
    private InitiativeDAO initiativeDAO;

    @Inject
    private AreaDAO areaDAO;

    @Override
    public void registrarUsuario(User user) throws ExceptionServiciosBancoIniciativas {
        try {
            userDAO.saveUser(user);
        } catch (PersistenceException ex) {
            throw new ExceptionServiciosBancoIniciativas(ex.getMessage(), ex);
        }
    }

    @Override
    public User consultarUsuario(String email) throws ExceptionServiciosBancoIniciativas {
        try {
            return userDAO.loadUser(email);
        } catch (PersistenceException ex) {
            throw new ExceptionServiciosBancoIniciativas(ex.getMessage(), ex);
        }
    }
    
    @Override
    public List<User> consultarUsuariosRol(Role role) throws ExceptionServiciosBancoIniciativas {
        try {
            return userDAO.loadUsersRole(role);
        } catch (PersistenceException ex) {
            throw new ExceptionServiciosBancoIniciativas(ex.getMessage(), ex);
        }
    }
    
    @Override
    public void actualizarRolUsuario(String email, String role) throws ExceptionServiciosBancoIniciativas {
        try {
            userDAO.updateRoleUser(email, role);
        } catch (PersistenceException ex) {
            throw new ExceptionServiciosBancoIniciativas(ex.getMessage(), ex);
        }
    }
    
    @Override
    public int consultarIdIniciativa() throws ExceptionServiciosBancoIniciativas {
        try {
            return initiativeDAO.loadInitiativeId();
        } catch (PersistenceException ex) {
            throw new ExceptionServiciosBancoIniciativas(ex.getMessage(), ex);
        }
    }

    @Override
    public void registrarIniciativa(Initiative ini) throws ExceptionServiciosBancoIniciativas {
        try {
            initiativeDAO.saveInitiative(ini);
        } catch (PersistenceException ex) {
            throw new ExceptionServiciosBancoIniciativas(ex.getMessage(), ex);
        }
    }

    @Override
    public Initiative consultarIniciativa(int id) throws ExceptionServiciosBancoIniciativas {
        try {
            return initiativeDAO.loadInitiative(id);
        } catch (PersistenceException ex) {
            throw new ExceptionServiciosBancoIniciativas(ex.getMessage(), ex);
        }
    }

    @Override
    public Area consultarArea(int id) throws ExceptionServiciosBancoIniciativas {
        try {
            return areaDAO.loadArea(id);
        } catch (PersistenceException ex) {
            throw new ExceptionServiciosBancoIniciativas(ex.getMessage(), ex);
        }
    }

    @Override
    public List<Area> consultarAreas() throws ExceptionServiciosBancoIniciativas {
        try {
            return areaDAO.loadAreas();
        } catch (PersistenceException ex) {
            throw new ExceptionServiciosBancoIniciativas(ex.getMessage(), ex);
        }
    }

    @Override
    public void registrarArea(Area area) throws ExceptionServiciosBancoIniciativas {
        try {
            areaDAO.saveArea(area);
        } catch (PersistenceException ex) {
            throw new ExceptionServiciosBancoIniciativas(ex.getMessage(), ex);
        }
    }

    @Override
    public void registrarEstadoIniciativa(InitiativeStatus iniSta) throws ExceptionServiciosBancoIniciativas {
        try {
            initiativeDAO.saveInitiativeStatus(iniSta);
        } catch (PersistenceException ex) {
            throw new ExceptionServiciosBancoIniciativas(ex.getMessage(), ex);
        }
    }

    @Override
    public InitiativeStatus consultarEstadoIniciativas(int id) throws ExceptionServiciosBancoIniciativas {
        try {
            return initiativeDAO.loadInitiativesStatus(id);
        } catch (PersistenceException ex) {
            throw new ExceptionServiciosBancoIniciativas(ex.getMessage(), ex);
        }
    }

    @Override
    public void updateInitiativeStatus(int id, int iniStat) throws ExceptionServiciosBancoIniciativas {
        try {
            initiativeDAO.updateInitiativeStatus(id, iniStat);
        } catch (PersistenceException ex) {
            throw new ExceptionServiciosBancoIniciativas(ex.getMessage(), ex);
        }
    }

    @Override
    public List<Initiative> consultInitiativeForKeyWord(String keyWord) throws ExceptionServiciosBancoIniciativas {
        try {
            return initiativeDAO.consultInitiativeForKeyWord(keyWord);
        } catch (PersistenceException ex) {
            throw new ExceptionServiciosBancoIniciativas(ex.getMessage(), ex);
        }
    }

}
