package edu.eci.pdsw.sampleprj.dao.mybatis;

import com.google.inject.Inject;
import edu.eci.pdsw.sampleprj.dao.UserDAO;
import edu.eci.pdsw.sampleprj.dao.mybatis.mappers.UserMapper;
import edu.eci.pdsw.samples.entities.User;
import org.apache.ibatis.exceptions.PersistenceException;

/**
 *
 * @author ECI-FACS
 */
public class MyBATISUserDAO implements UserDAO{
    
    @Inject
    UserMapper userMapper;
    
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
}
