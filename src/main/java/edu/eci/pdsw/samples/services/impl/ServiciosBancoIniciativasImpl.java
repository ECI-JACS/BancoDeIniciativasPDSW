package edu.eci.pdsw.samples.services.impl;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import edu.eci.pdsw.sampleprj.dao.AreaDAO;
import edu.eci.pdsw.sampleprj.dao.InitiativeDAO;
import edu.eci.pdsw.sampleprj.dao.UserDAO;
import edu.eci.pdsw.sampleprj.dao.CommentDAO;
import edu.eci.pdsw.sampleprj.dao.VoteDAO;
import edu.eci.pdsw.samples.entities.Area;
import edu.eci.pdsw.samples.entities.Comment;
import edu.eci.pdsw.samples.entities.Initiative;
import edu.eci.pdsw.samples.entities.InitiativeStatus;
import edu.eci.pdsw.samples.entities.User;
import edu.eci.pdsw.samples.entities.Vote;
import edu.eci.pdsw.samples.services.ExceptionServiciosBancoIniciativas;
import edu.eci.pdsw.samples.services.ServiciosBancoIniciativas;
import java.sql.Date;

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

    @Inject
    private CommentDAO commentDAO;
    
    @Inject
    private VoteDAO voteDAO;

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
    public List<User> consultarUsuariosPorBusqueda(String nombres, String apellidos, String email, int carnet, int idArea, String role) throws ExceptionServiciosBancoIniciativas {
        try {
            return userDAO.loadUsersForSearch(nombres, apellidos, email, carnet, idArea, role);
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
    public void actualizarIniciativa(int id, String descripcion, String detalle, String palabrasClave) throws ExceptionServiciosBancoIniciativas {
        try {
            initiativeDAO.updateInitiative(id, descripcion, detalle, palabrasClave);
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
    public List<Initiative> consultarIniciativas() throws ExceptionServiciosBancoIniciativas {
        try {
            return initiativeDAO.loadInitiatives();
        } catch (PersistenceException ex) {
            throw new ExceptionServiciosBancoIniciativas(ex.getMessage(), ex);
        }
    }

    @Override
    public List<Initiative> consultarIniciativasUsuario(String email) throws ExceptionServiciosBancoIniciativas {
        try {
            return initiativeDAO.loadInitiativesUser(email);
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
    public List<InitiativeStatus> consultarEstadosIniciativas() throws ExceptionServiciosBancoIniciativas {
        try {
            return initiativeDAO.loadAllInitiativesStatus();
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
    public List<Initiative> consultarIniciativasPorBusqueda(String palabrasClave, String proponente, Date fechaPropuesta, int dependencia, int estado) throws ExceptionServiciosBancoIniciativas {
        try {
            return initiativeDAO.loadInitiativeForSearch(palabrasClave, proponente, fechaPropuesta, dependencia, estado);
        } catch (PersistenceException ex) {
            throw new ExceptionServiciosBancoIniciativas(ex.getMessage(), ex);
        }
    }

    @Override
    public void deleteUser(String email) throws ExceptionServiciosBancoIniciativas {
        try {
            userDAO.deleteUser(email);
        } catch (PersistenceException ex) {
            throw new ExceptionServiciosBancoIniciativas(ex.getMessage(), ex);
        }
    }

    @Override
    public void insertarComentarioEnUnaIniciativa(Comment com) throws ExceptionServiciosBancoIniciativas {
        try {
            commentDAO.insertComment(com);
        } catch (PersistenceException ex) {
            throw new ExceptionServiciosBancoIniciativas(ex.getMessage(), ex);
        }
    }

    @Override
    public int consultarIdComentarios() throws ExceptionServiciosBancoIniciativas {
        try {
            return initiativeDAO.loadCommentId();
        } catch (PersistenceException ex) {
            throw new ExceptionServiciosBancoIniciativas(ex.getMessage(), ex);
        }
    }

    @Override
    public void votar(Vote voto) throws ExceptionServiciosBancoIniciativas {
        try {
            voteDAO.saveVote(voto);
        } catch (PersistenceException ex) {
            throw new ExceptionServiciosBancoIniciativas(ex.getMessage(), ex);
        }
    }

    @Override
    public int consultarIdVotos() throws ExceptionServiciosBancoIniciativas {
        try {
            return voteDAO.loadVoteId();
        } catch (PersistenceException ex) {
            throw new ExceptionServiciosBancoIniciativas(ex.getMessage(), ex);
        }
    }

    @Override
    public void quitarVoto(String userEmail, int iniciativaId) throws ExceptionServiciosBancoIniciativas {
        try {
            voteDAO.deleteVote(userEmail, iniciativaId);
        } catch (PersistenceException ex) {
            throw new ExceptionServiciosBancoIniciativas(ex.getMessage(), ex);
        }
    }
}
