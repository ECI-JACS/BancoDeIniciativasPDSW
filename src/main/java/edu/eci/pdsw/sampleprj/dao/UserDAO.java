package edu.eci.pdsw.sampleprj.dao;

import edu.eci.pdsw.samples.entities.User;
import org.apache.ibatis.exceptions.PersistenceException;
/**
 *
 * @author 2137441
 */
public interface UserDAO {
     public void saveUser() throws PersistenceException;    
     public User load(String correo) throws PersistenceException;     
}
