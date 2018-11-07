package edu.eci.pdsw.sampleprj.dao.mybatis.mappers;

import edu.eci.pdsw.samples.entities.Role;
import edu.eci.pdsw.samples.entities.User;
import java.util.List;

import org.apache.ibatis.annotations.Param;

/**
 *
 * @author ECI-FACS
 */
public interface UserMapper {
    
    public void insertarUsuario(@Param("user") User user);
    
    public User consultarUsuario(@Param("email") String email);

    public List<User> consultarUsuariosRol(@Param("role") Role role);

    public void actualizarUsuariosRol(@Param("email") String email, @Param("role") Role role);
}
