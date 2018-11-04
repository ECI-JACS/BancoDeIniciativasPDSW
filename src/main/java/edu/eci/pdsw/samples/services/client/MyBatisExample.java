package edu.eci.pdsw.samples.services.client;

import java.sql.SQLException;
import java.io.IOException;
import java.io.InputStream;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.session.SqlSession;
import java.sql.Connection;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import edu.eci.pdsw.samples.entities.Area;
import edu.eci.pdsw.samples.entities.Initiative;
import edu.eci.pdsw.samples.entities.InitiativeStatus;
import edu.eci.pdsw.samples.entities.Role;
import edu.eci.pdsw.samples.entities.User;
import edu.eci.pdsw.samples.entities.UserStatus;
import edu.eci.pdsw.samples.services.ExceptionServiciosBancoIniciativas;
import edu.eci.pdsw.samples.services.ServiciosBancoIniciativasFactory;
import edu.eci.pdsw.samples.services.ServiciosBancoIniciativas;
import java.sql.DriverManager;


/**
 *
 * @author ECI-JACS
 */
public class MyBatisExample {
    
    /**
     * Método que construye una fábrica de sesiones de MyBatis a partir del
     * archivo de configuración ubicado en src/main/resources
     *
     * @return instancia de SQLSessionFactory
     */
    public static SqlSessionFactory getSqlSessionFactory() {
        SqlSessionFactory sqlSessionFactory = null;
        if (sqlSessionFactory == null) {
            InputStream inputStream;
            try {
                inputStream = Resources.getResourceAsStream("mybatis-config.xml");
                sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            } catch (IOException e) {
                throw new RuntimeException(e.getCause());
            }
        }
        return sqlSessionFactory;
    }
    
    /**
     * Programa principal para probar MyBatis
     *
     * @param args
     * @throws SQLException
     */
    public static void main(String args[]) throws SQLException {
        
        SqlSessionFactory sessionfact = getSqlSessionFactory();
        SqlSession sqlss;
        sqlss = sessionfact.openSession();
        
        ServiciosBancoIniciativasFactory servicesInitiativesBankFactory = ServiciosBancoIniciativasFactory.getInstance();
        ServiciosBancoIniciativas serviciosBancoIniciativas = servicesInitiativesBankFactory.getServiciosBancoIniciativas();

        try {
            //serviciosBancoIniciativas.registrarArea(new Area(1, "Sistemas"));
            System.out.println(serviciosBancoIniciativas.consultarArea(1));
            //serviciosBancoIniciativas.registrarUsuario(new User("Carlos Andrés", "Medina Rivas", "carlos.medina-ri@mail.escuelaing.edu.co", 2125262, UserStatus.ACTIVO, Role.ADMINISTRADOR, new Area(1, "Sistemas")));
            System.out.println(serviciosBancoIniciativas.consultarUsuario("carlos.medina-ri@mail.escuelaing.edu.co"));
            //serviciosBancoIniciativas.registrarEstadoIniciativa(new InitiativeStatus(1, "En espera de revisión"));
            System.out.println(serviciosBancoIniciativas.consultarEstadoIniciativas(1));
            //Para crear una iniciativa.
            String keyWords = "edificio,investigación,proyectos,sustentaciones,sistemas";
            //serviciosBancoIniciativas.registrarIniciativa(new Initiative(1, "Nuevo edificio de sistemas", "Este nuevo edificio será para sustentaciones, investigaciones y realización de proyectos", new Date(2018-1900,10,2), null, keyWords, new User("Carlos Andrés", "Medina Rivas", "carlos.medina-ri@mail.escuelaing.edu.co", 2125262, UserStatus.ACTIVO, Role.ADMINISTRADOR, new Area(1, "Sistemas")), new InitiativeStatus(1, "En espera de revisión")));
            System.out.println(serviciosBancoIniciativas.consultarIniciativa(1));
        } catch (ExceptionServiciosBancoIniciativas ex) {
            Logger.getLogger(MyBatisExample.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        sqlss.commit();
        sqlss.close();
    }
}
