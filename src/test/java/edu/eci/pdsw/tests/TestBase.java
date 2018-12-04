package edu.eci.pdsw.tests;

import org.junit.*;
import org.mybatis.guice.XMLMyBatisModule;

import com.google.inject.Guice;
import com.google.inject.Injector;

import edu.eci.pdsw.sampleprj.dao.AreaDAO;
import edu.eci.pdsw.sampleprj.dao.CommentDAO;
import edu.eci.pdsw.sampleprj.dao.InitiativeDAO;
import edu.eci.pdsw.sampleprj.dao.UserDAO;
import edu.eci.pdsw.sampleprj.dao.VoteDAO;
import edu.eci.pdsw.sampleprj.dao.mybatis.MyBATISAreaDAO;
import edu.eci.pdsw.sampleprj.dao.mybatis.MyBATISCommentDAO;
import edu.eci.pdsw.sampleprj.dao.mybatis.MyBATISInitiativeDAO;
import edu.eci.pdsw.sampleprj.dao.mybatis.MyBATISUserDAO;
import edu.eci.pdsw.sampleprj.dao.mybatis.MyBATISVoteDAO;
import edu.eci.pdsw.samples.services.ServiciosBancoIniciativas;
import edu.eci.pdsw.samples.services.impl.ServiciosBancoIniciativasImpl;



public class TestBase {
    protected Injector injector = Guice.createInjector(new XMLMyBatisModule() {
            @Override
            protected void initialize() {
                setEnvironmentId("test");
                setClassPathResource("h2-mybatis-config.xml");
                
                bind(ServiciosBancoIniciativas.class).to(ServiciosBancoIniciativasImpl.class);
                bind(UserDAO.class).to(MyBATISUserDAO.class);
                bind(InitiativeDAO.class).to(MyBATISInitiativeDAO.class);
                bind(AreaDAO.class).to(MyBATISAreaDAO.class);
                bind(CommentDAO.class).to(MyBATISCommentDAO.class);
                bind(VoteDAO.class).to(MyBATISVoteDAO.class);
            }
    });
    
    @Before
    public void setup() {
        injector.injectMembers(this);
    }
}