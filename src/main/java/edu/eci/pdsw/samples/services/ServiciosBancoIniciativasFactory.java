/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.pdsw.samples.services;

import org.mybatis.guice.XMLMyBatisModule;
import org.mybatis.guice.datasource.helper.JdbcHelper;

import static com.google.inject.Guice.createInjector;
import com.google.inject.Injector;
import edu.eci.pdsw.samples.persistence.DaoComentario;
import edu.eci.pdsw.samples.persistence.mybatisimpl.MyBatisDaoComentario;
import edu.eci.pdsw.samples.services.impl.ServiciosSuscripcionesImpl;

/**
 *
 * @author hcadavid
 */
public class ServiciosXFactory {

    private static ServiciosXFactory instance = new ServiciosXFactory();

    private static Injector injector;
    private static Injector testingInjector;

    private ServiciosXFactory() {
        injector = createInjector(new XMLMyBatisModule() {

            @Override
            protected void initialize() {
                install(JdbcHelper.MySQL);
                setClassPathResource("mybatis-config.xml");
                bind(ServiciosX.class).to(ServiciosSuscripcionesImpl.class);
                bind(DaoComentario.class).to(MyBatisDaoComentario.class);

            }

        }
        );

        testingInjector = createInjector(new XMLMyBatisModule() {

            @Override
            protected void initialize() {
                install(JdbcHelper.MySQL);
                setClassPathResource("h2-mybatis-config.xml");
                bind(ServiciosX.class).to(ServiciosSuscripcionesImpl.class);
                bind(DaoComentario.class).to(MyBatisDaoComentario.class);

            }

        }
        );

    }

    public ServiciosSuscripciones getSuscriptionServices() {
        return injector.getInstance(ServiciosSuscripciones.class);
    }

    public ServiciosSuscripciones getSuscriptionServicesForTesting() {
        return testingInjector.getInstance(ServiciosSuscripciones.class);
    }

    public static ServiciosXFactory getInstance() {
        return instance;
    }

    public static void main(String a[]) throws ExcepcionServiciosSuscripciones {
        System.out.println(ServiciosXFactory.getInstance().getSuscriptionServices().comenteriosMasBajosPorRangoEdad(1, 10));
    }

}
