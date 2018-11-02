/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.pdsw.sampleprj.dao.mybatis;

import com.google.inject.Inject;
import edu.eci.pdsw.sampleprj.dao.UserDAO;
import edu.eci.pdsw.sampleprj.dao.mybatis.mappers.UserMapper;
import edu.eci.pdsw.samples.entities.User;
import org.apache.ibatis.exceptions.PersistenceException;

/**
 *
 * @author 2137441
 */
public class MyBATISUserDAO implements UserDAO{
    @Inject
    UserMapper usuarioMapper;

    @Override
    public User load(String email) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void saveUser() throws PersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
