package edu.eci.pdsw.sampleprj.dao.mybatis.mappers;

import edu.eci.pdsw.samples.entities.Initiative;
import edu.eci.pdsw.samples.entities.InitiativeStatus;

import java.util.List;

import org.apache.ibatis.annotations.Param;

/**
 *
 * @author ECI-JACS
 */
public interface InitiativeMapper {
    
    public void insertarIniciativa(@Param("ini") Initiative ini);
    
    public Initiative consultarIniciativa(@Param("id") int id);
    
    public void insertarEstadoIniciativa(@Param("iniSta") InitiativeStatus iniSta);
    
    public void updateInitiativeStatus(@Param("id")int id,@Param("iniStat") int iniStat);
    
    public InitiativeStatus consultarEstadoIniciativas(@Param("id") int id);

    public List<Initiative> consultInitiativeForKeyWord(@Param("keyWord") String keyWord);
}
