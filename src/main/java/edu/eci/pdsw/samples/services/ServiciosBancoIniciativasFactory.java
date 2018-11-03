package edu.eci.pdsw.samples.services;

import org.mybatis.guice.XMLMyBatisModule;
import org.mybatis.guice.datasource.helper.JdbcHelper;

import static com.google.inject.Guice.createInjector;
import com.google.inject.Injector;
import edu.eci.pdsw.sampleprj.dao.AreaDAO;
import edu.eci.pdsw.sampleprj.dao.InitiativeDAO;
import edu.eci.pdsw.sampleprj.dao.UserDAO;
import edu.eci.pdsw.sampleprj.dao.mybatis.MyBATISInitiativeDAO;
import edu.eci.pdsw.sampleprj.dao.mybatis.MyBATISUserDAO;
import edu.eci.pdsw.sampleprj.dao.mybatis.MyBATISAreaDAO;

import edu.eci.pdsw.samples.services.impl.ServiciosBancoIniciativasImpl;

import java.util.Optional;

/**
 *
 * @author ECI-JACS
 */
public class ServiciosBancoIniciativasFactory {
    
    private static ServiciosBancoIniciativasFactory instance = new ServiciosBancoIniciativasFactory();

    private static Optional<Injector> optInjector;

    private Injector myBatisInjector(String env, String pathResource) {
        return createInjector(new XMLMyBatisModule() {
            @Override
            protected void initialize() {
                install(JdbcHelper.PostgreSQL);
                setEnvironmentId(env);
                setClassPathResource(pathResource);
                bind(ServiciosBancoIniciativas.class).to(ServiciosBancoIniciativasImpl.class);
                //Bind de los DAOs
                bind(UserDAO.class).to(MyBATISUserDAO.class);
                bind(InitiativeDAO.class).to(MyBATISInitiativeDAO.class);
                bind(AreaDAO.class).to(MyBATISAreaDAO.class);
            }
        });
    }

    private ServiciosBancoIniciativasFactory() {
        optInjector = Optional.empty();
    }

    public ServiciosBancoIniciativas getServiciosBancoIniciativas() {
        if (!optInjector.isPresent()) {
            optInjector = Optional.of(myBatisInjector("development", "mybatis-config.xml"));
        }
        return optInjector.get().getInstance(ServiciosBancoIniciativas.class);
    }

    public ServiciosBancoIniciativas getServiciosBancoIniciativasTesting() {
        if (!optInjector.isPresent()) {
            optInjector = Optional.of(myBatisInjector("test", "mybatis-config-h2.xml"));
        }
        return optInjector.get().getInstance(ServiciosBancoIniciativas.class);
    }

    public static ServiciosBancoIniciativasFactory getInstance() {
        return instance;
    }
}
