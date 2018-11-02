/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.pdsw.sampleprj.dao.mybatis;

import com.google.inject.Inject;
import edu.eci.pdsw.sampleprj.dao.UsuarioDAO;
import edu.eci.pdsw.sampleprj.dao.mybatis.mappers.UsuarioMapper;
import edu.eci.pdsw.samples.entities.Usuario;
import org.apache.ibatis.exceptions.PersistenceException;

/**
 *
 * @author 2137441
 */
public class MyBATISUsuarioDAO implements UsuarioDAO{
    @Inject
    UsuarioMapper usuarioMapper;

    @Override
    public Usuario load(String correo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void saveUsuario() throws PersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
