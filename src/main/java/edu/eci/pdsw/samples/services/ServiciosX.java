package edu.eci.pdsw.samples.services;


import java.sql.Date;
import java.util.List;
import java.util.Set;
import edu.eci.pdsw.samples.entities.Comentario;

/**
 *
 * @author 2106913
 */
public interface ServiciosX {

    
    /**
     * Consultar los comentarios mas bajos (menores a 5) en el rango de 
     * edad (A..B], es decir, donde A < edad >= B
     * @param a asd
     * @param b asdas
     * @return el listado de comentarios cuyo puntaje es mejor que n
     * @throws ServiceFacadeException si n es negativo o si se presenta un error
     * a nivel de base de datos
     */
    public Set<Comentario> comenteriosMasBajosPorRangoEdad(int a,int b) throws ExcepcionServiciosSuscripciones;
    

    /**
     * Consultar todos los comentarios registrados en el sistema, ordenados
     * desde el mas reciente hasta el mas antiguo
     * @return el listado de comentarios, con sus respectivos suscriptores
     */
    public Set<Comentario> comenteriosRegistrados() throws ExcepcionServiciosSuscripciones;

    

}
