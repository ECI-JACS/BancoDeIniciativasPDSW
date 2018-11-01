package edu.eci.pdsw.persistence;

import edu.eci.pdsw.entities.Usuario;
import org.apache.ibatis.exceptions.PersistenceException;
/**
 *
 * @author 2137441
 */
public interface UsuarioDAO {
     public void saveUsuario() throws PersistenceException;    
     public Usuario load(String correo) throws PersistenceException;     
}
