package edu.eci.pdsw.samples.services.client;

import java.sql.SQLException;
import java.io.IOException;
import java.io.InputStream;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.session.SqlSession;
import java.sql.Date;
import java.util.List;
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
            //System.out.println(serviciosBancoIniciativas.consultarAreas());
            //System.out.println(serviciosBancoIniciativas.consultarArea(1));
            //serviciosBancoIniciativas.registrarArea(new Area(1, "Decanatura de Ingenería de Sistemas"));
            //serviciosBancoIniciativas.registrarArea(new Area(2, "Unidad de Proyectos"));
            //serviciosBancoIniciativas.registrarArea(new Area(3, "Vicerrectoría"));
            //serviciosBancoIniciativas.registrarArea(new Area(4, "Compras"));
            System.out.println(serviciosBancoIniciativas.consultarAreas());
            System.out.println(serviciosBancoIniciativas.consultarArea(1));
            //serviciosBancoIniciativas.registrarUsuario(new User("Carlos Andrés", "Medina Rivas", "carlos.medina-ri@mail.escuelaing.edu.co", 2125262, UserStatus.ACTIVO, Role.ADMINISTRADOR, new Area(1, "Sistemas")));
            System.out.println(serviciosBancoIniciativas.consultarUsuario("carlos.medina-ri@mail.escuelaing.edu.co"));
            //serviciosBancoIniciativas.registrarEstadoIniciativa(new InitiativeStatus(1, "En espera de revisión"));
            //-----Creación de un nuevo estado de iniciativa---------
            //System.out.println(serviciosBancoIniciativas.consultarEstadoIniciativas(1));
            //serviciosBancoIniciativas.registrarEstadoIniciativa(new InitiativeStatus(2, "En revisión"));
            //serviciosBancoIniciativas.registrarEstadoIniciativa(new InitiativeStatus(3, "Proyecto"));
            //serviciosBancoIniciativas.registrarEstadoIniciativa(new InitiativeStatus(4, "Solucionado"));
            //-----Para poder poder hacer la prueba debí crear otro estado.-----
            //System.out.println(serviciosBancoIniciativas.consultarEstadoIniciativas(2));
            //System.out.println(serviciosBancoIniciativas.consultarIniciativa(1));
            //-----Para crear una iniciativa-------
            //serviciosBancoIniciativas.updateInitiativeStatus(1, 1);
            // ----Para actualizar el estdo de una iniciativa-------
            //System.out.println(serviciosBancoIniciativas.consultarIniciativa(1));
            //serviciosBancoIniciativas.updateInitiativeStatus(1, 2);
            //------- modifiqué el estado de la iniciativa 1 de 1 a 2, es decir de "En espera de revisión" a "En revisión"
            //System.out.println(serviciosBancoIniciativas.consultarIniciativa(1));
            //String keyWords = "edificio,investigación,proyectos,sustentaciones,sistemas";
            ///serviciosBancoIniciativas.registrarIniciativa(new Initiative(1, "Nuevo edificio de sistemas", "Este nuevo edificio será para sustentaciones, investigaciones y realización de proyectos", new Date(2018-1900,10,2), null, keyWords, new User("Carlos Andrés", "Medina Rivas", "carlos.medina-ri@mail.escuelaing.edu.co", 2125262, UserStatus.ACTIVO, Role.ADMINISTRADOR, new Area(1, "Sistemas")), new InitiativeStatus(1, "En espera de revisión")));
            //List<Initiative> iniciativas = serviciosBancoIniciativas.consultInitiativeForKeyWord("edificio,iniciativa");
            //System.out.println("############### Iniciativas consultadas por palabras clave: ");
            /*if (iniciativas.size() > 0) {
                for (int indice = 0; indice < iniciativas.size(); indice++) {
                    System.out.println(iniciativas.get(indice).toString());
                }
            } else {
                System.out.println("No se encontraron reslutados");
            }*/
            //System.out.println(serviciosBancoIniciativas.consultarUsuariosRol(Role.PUBLICO));
            //serviciosBancoIniciativas.actualizarRolUsuario("amalia.alfonso@mail.escuelaing.edu.co", Role.PUBLICO.toString());
            ////System.out.println(serviciosBancoIniciativas.consultarIdIniciativa());
            //System.out.println(serviciosBancoIniciativas.consultarIniciativas());
            //System.out.println(serviciosBancoIniciativas.consultarEstadosIniciativas());
            System.out.println(serviciosBancoIniciativas.consultarUsuario("amalia.alfonso@mail.escuelaing.edu.co"));
            
        } catch (ExceptionServiciosBancoIniciativas ex) {
            Logger.getLogger(MyBatisExample.class.getName()).log(Level.SEVERE, null, ex);
        }

        sqlss.commit();
        sqlss.close();
    }
}
