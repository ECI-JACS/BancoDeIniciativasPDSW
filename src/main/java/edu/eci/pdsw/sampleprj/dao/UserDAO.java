package edu.eci.pdsw.sampleprj.dao;

import edu.eci.pdsw.samples.entities.User;

import org.apache.ibatis.exceptions.PersistenceException;

/**
 *
 * @author ECI-JACS
 */
public interface UserDAO {
    
     public void saveUser(User user) throws PersistenceException;
     
     public User loadUser(String email) throws PersistenceException;     
}