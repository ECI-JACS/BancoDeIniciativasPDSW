package edu.eci.pdsw.samples.services;

import org.mybatis.guice.XMLMyBatisModule;
import org.mybatis.guice.datasource.helper.JdbcHelper;

import static com.google.inject.Guice.createInjector;
import com.google.inject.Injector;

import edu.eci.pdsw.samples.services.impl.ServicesInitiativesBankImpl;

import java.util.Optional;

/**
 *
 * @author ECI-JACS
 */
public class ServicesInitiativesBankFactory {
    
    private static ServicesInitiativesBankFactory instance = new ServicesInitiativesBankFactory();

    private static Optional<Injector> optInjector;

    private Injector myBatisInjector(String pathResource) {
        return createInjector(new XMLMyBatisModule() {
            @Override
            protected void initialize() {
                install(JdbcHelper.PostgreSQL);
                setClassPathResource(pathResource);
                bind(ServicesInitiativesBank.class).to(ServicesInitiativesBankImpl.class);
                //Bind de los DAOs
            }
        });
    }

    private ServicesInitiativesBankFactory() {
        optInjector = Optional.empty();
    }

    public ServicesInitiativesBank getServiciosBancoIniciativas() {
        if (!optInjector.isPresent()) {
            optInjector = Optional.of(myBatisInjector("mybatis-config.xml"));
        }
        return optInjector.get().getInstance(ServicesInitiativesBank.class);
    }

    public ServicesInitiativesBank getServiciosBancoIniciativasTesting() {
        if (!optInjector.isPresent()) {
            optInjector = Optional.of(myBatisInjector("mybatis-config-h2.xml"));
        }
        return optInjector.get().getInstance(ServicesInitiativesBank.class);
    }

    public static ServicesInitiativesBankFactory getInstance() {
        return instance;
    }
}
