package edu.eci.pdsw.sampleprj.dao;

import edu.eci.pdsw.samples.entities.Area;
import java.util.List;
import org.apache.ibatis.exceptions.PersistenceException;

/**
 *
 * @author ECI-FACS
 */
public interface AreaDAO {
    
    public void saveArea(Area area) throws PersistenceException;
    
    public Area loadArea(int id) throws PersistenceException;
    
    public List<Area> loadAreas() throws PersistenceException;
}
