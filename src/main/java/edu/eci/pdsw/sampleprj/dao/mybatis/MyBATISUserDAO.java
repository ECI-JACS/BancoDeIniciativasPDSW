package edu.eci.pdsw.sampleprj.dao.mybatis;

import com.google.inject.Inject;
import edu.eci.pdsw.sampleprj.dao.UserDAO;
import edu.eci.pdsw.sampleprj.dao.mybatis.mappers.UserMapper;
import edu.eci.pdsw.samples.entities.Role;
import edu.eci.pdsw.samples.entities.User;
import java.util.List;
import org.apache.ibatis.exceptions.PersistenceException;

/**
 *
 * @author ECI-FACS
 */
public class MyBATISUserDAO implements UserDAO {
    
    @Inject
    private UserMapper userMapper;
    
    @Override
    public void saveUser(User user) throws PersistenceException {
        try {
            userMapper.insertarUsuario(user);
        } catch (PersistenceException e) {
            throw new PersistenceException("Error al insertar el usuario " + user.toString(), e);
        }
    }

    @Override
    public User loadUser(String email) throws PersistenceException {
        try {
            return userMapper.consultarUsuario(email);
        } catch (PersistenceException e) {
            throw new PersistenceException("Error al consultar el usuario con email " + email, e);
        }
    }

    @Override
    public List<User> loadUsersRole(Role role) throws PersistenceException {
        try {
            return userMapper.consultarUsuariosRol(role);
        } catch (PersistenceException e) {
            throw new PersistenceException("Error al consultar los usuarios por el role " + role, e);
        }
    }

    @Override
    public void updateRoleUser(String email, String role) throws PersistenceException {
        try {
            userMapper.actualizarUsuariosRol(email, role);
        } catch (PersistenceException e) {
            throw new PersistenceException("Error al actualizar el role del usuario " + email, e);
        }
    }
}
