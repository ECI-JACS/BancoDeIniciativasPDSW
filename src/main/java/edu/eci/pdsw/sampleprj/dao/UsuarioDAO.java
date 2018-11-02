package edu.eci.pdsw.sampleprj.dao;

import edu.eci.pdsw.samples.entities.Usuario;
import org.apache.ibatis.exceptions.PersistenceException;
/**
 *
 * @author 2137441
 */
public interface UsuarioDAO {
     public void saveUsuario() throws PersistenceException;    
     public Usuario load(String correo) throws PersistenceException;     
}
