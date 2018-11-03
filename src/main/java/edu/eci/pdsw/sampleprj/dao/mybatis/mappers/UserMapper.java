package edu.eci.pdsw.sampleprj.dao.mybatis.mappers;

import edu.eci.pdsw.samples.entities.User;

import org.apache.ibatis.annotations.Param;

/**
 *
 * @author ECI-FACS
 */
public interface UserMapper {
    
    public void insertarUsuario(@Param("user") User user);
    
    public User consultarUsuario(@Param("email") String email);
}
