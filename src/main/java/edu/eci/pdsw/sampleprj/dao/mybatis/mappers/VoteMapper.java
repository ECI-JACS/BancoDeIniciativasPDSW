package edu.eci.pdsw.sampleprj.dao.mybatis.mappers;

import org.apache.ibatis.annotations.Param;

import edu.eci.pdsw.samples.entities.Vote;

/**
 *
 * @author ECI-JACS
 */
public interface VoteMapper {
    
    public void votar(@Param("voto") Vote voto);

    public int consultarIdVoto();

    public void quitarVoto(@Param("userEmail") String userEmail, @Param("idIniciativa") int iniciativaId);
    
}