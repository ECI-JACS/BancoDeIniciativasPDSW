package edu.eci.pdsw.sampleprj.dao;

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

    public List<User> loadUsersForSearch(String nombres, String apellidos, String email, int carnet, int idArea, String role) throws PersistenceException;
    
    public void updateRoleUser(String email, String role) throws PersistenceException;
    
    public void deleteUser(String email) throws PersistenceException;
    
}
