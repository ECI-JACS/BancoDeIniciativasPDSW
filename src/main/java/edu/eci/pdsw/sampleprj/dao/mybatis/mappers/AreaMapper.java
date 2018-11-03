package edu.eci.pdsw.sampleprj.dao.mybatis.mappers;

import edu.eci.pdsw.samples.entities.Area;

import org.apache.ibatis.annotations.Param;

/**
 *
 * @author ECI-JACS
 */
public interface AreaMapper {
    
    public void insertarArea(@Param("area") Area area);
    
    public Area consultarArea(@Param("id") int id);
}
