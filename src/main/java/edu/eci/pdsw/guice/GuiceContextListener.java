package edu.eci.pdsw.guice;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.mybatis.guice.XMLMyBatisModule;
import org.mybatis.guice.datasource.helper.JdbcHelper;
import com.google.inject.Guice;
import com.google.inject.Injector;
import edu.eci.pdsw.sampleprj.dao.UserDAO;
import edu.eci.pdsw.sampleprj.dao.InitiativeDAO;
import edu.eci.pdsw.sampleprj.dao.AreaDAO;
import edu.eci.pdsw.sampleprj.dao.mybatis.MyBATISUserDAO;
import edu.eci.pdsw.sampleprj.dao.mybatis.MyBATISInitiativeDAO;
import edu.eci.pdsw.sampleprj.dao.mybatis.MyBATISAreaDAO;
import edu.eci.pdsw.samples.services.ServiciosBancoIniciativas;
import edu.eci.pdsw.samples.services.impl.ServiciosBancoIniciativasImpl;

/**
 *
 * @author ECI-FACS
 */
public class GuiceContextListener implements ServletContextListener {

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        ServletContext servletContext = servletContextEvent.getServletContext();
        servletContext.removeAttribute(Injector.class.getName());
    }

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        Injector injector = Guice.createInjector(new XMLMyBatisModule() {
            @Override
            protected void initialize() {
                install(JdbcHelper.PostgreSQL);
                setClassPathResource("mybatis-config.xml");
                // TODO Add service class associated to Stub implementation
                bind(ServiciosBancoIniciativas.class).to(ServiciosBancoIniciativasImpl.class);
                bind(UserDAO.class).to(MyBATISUserDAO.class);
                bind(InitiativeDAO.class).to(MyBATISInitiativeDAO.class);
                bind(AreaDAO.class).to(MyBATISAreaDAO.class);
            }
        });

        servletContextEvent.getServletContext().setAttribute(Injector.class.getName(), injector);
    }
}
