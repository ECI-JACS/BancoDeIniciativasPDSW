package edu.eci.pdsw.samples.services;

import org.mybatis.guice.XMLMyBatisModule;
import org.mybatis.guice.datasource.helper.JdbcHelper;

import static com.google.inject.Guice.createInjector;
import com.google.inject.Injector;

import edu.eci.pdsw.samples.services.impl.ServiciosBancoIniciativasImpl;

import java.util.Optional;

/**
 *
 * @author ECI-JACS
 */
public class ServiciosBancoIniciativasFactory {
    
    private static ServiciosBancoIniciativasFactory instance = new ServiciosBancoIniciativasFactory();

    private static Optional<Injector> optInjector;

    private Injector myBatisInjector(String pathResource) {
        return createInjector(new XMLMyBatisModule() {
            @Override
            protected void initialize() {
                install(JdbcHelper.PostgreSQL);
                setClassPathResource(pathResource);
                bind(ServiciosBancoIniciativas.class).to(ServiciosBancoIniciativasImpl.class);
                //Bind de los DAOs
            }
        });
    }

    private ServiciosBancoIniciativasFactory() {
        optInjector = Optional.empty();
    }

    public ServiciosBancoIniciativas getServiciosBancoIniciativas() {
        if (!optInjector.isPresent()) {
            optInjector = Optional.of(myBatisInjector("mybatis-config.xml"));
        }
        return optInjector.get().getInstance(ServiciosBancoIniciativas.class);
    }

    public ServiciosBancoIniciativas getServiciosBancoIniciativasTesting() {
        if (!optInjector.isPresent()) {
            optInjector = Optional.of(myBatisInjector("mybatis-config-h2.xml"));
        }
        return optInjector.get().getInstance(ServiciosBancoIniciativas.class);
    }

    public static ServiciosBancoIniciativasFactory getInstance() {
        return instance;
    }
}
