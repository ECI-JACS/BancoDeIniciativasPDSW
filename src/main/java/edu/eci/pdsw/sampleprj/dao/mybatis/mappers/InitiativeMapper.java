package edu.eci.pdsw.sampleprj.dao.mybatis.mappers;

import edu.eci.pdsw.samples.entities.Initiative;
import edu.eci.pdsw.samples.entities.InitiativeStatus;

import org.apache.ibatis.annotations.Param;

/**
 *
 * @author ECI-JACS
 */
public interface InitiativeMapper {
    
    public void insertarIniciativa(@Param("ini") Initiative ini);
    
    public Initiative consultarIniciativa(@Param("id") int id);
    
    public void insertarEstadoIniciativa(@Param("iniSta") InitiativeStatus iniSta);
    
    public void updateInitiativesStatus(@Param("id")int id,@Param("iniSta") InitiativeStatus iniStat);
    
    public InitiativeStatus consultarEstadoIniciativas(@Param("id") int id);

}
