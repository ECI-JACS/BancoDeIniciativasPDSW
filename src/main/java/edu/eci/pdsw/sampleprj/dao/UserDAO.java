package edu.eci.pdsw.sampleprj.dao;

import edu.eci.pdsw.samples.entities.Role;
import edu.eci.pdsw.samples.entities.User;
import java.util.List;

import org.apache.ibatis.exceptions.PersistenceException;

/**
 *
 * @author ECI-JACS
 */
public interface UserDAO {

    public void saveUser(User user) throws PersistenceException;

    public User loadUser(String email) throws PersistenceException;

    public List<User> loadUsersRole(Role role) throws PersistenceException;
    
    public void updateRoleUser(String email, String role) throws PersistenceException;
}
