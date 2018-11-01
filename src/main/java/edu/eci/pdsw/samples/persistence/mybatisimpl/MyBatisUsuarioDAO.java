/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.pdsw.samples.persistence.mybatisimpl;

import com.google.inject.Inject;
import edu.eci.pdsw.persistence.UsuarioDAO;
import edu.eci.pdsw.samples.persistence.mybatisimpl.mappers.UsuarioMapper;
import org.apache.ibatis.exceptions.PersistenceException;

/**
 *
 * @author 2137441
 */
public class MyBatisUsuarioDAO implements UsuarioDAO{
    @Inject
    UsuarioMapper usuarioMapper;

    @Override
    public void saveUsuario(Usuario usuario) throws PersistenceException {
        usuarioMapper.insertUser(this);
    }

    @Override
    public Usuario load(String correo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
