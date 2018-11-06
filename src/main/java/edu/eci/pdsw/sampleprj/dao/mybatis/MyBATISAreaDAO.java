package edu.eci.pdsw.sampleprj.dao.mybatis;

import com.google.inject.Inject;

import edu.eci.pdsw.sampleprj.dao.AreaDAO;
import edu.eci.pdsw.sampleprj.dao.mybatis.mappers.AreaMapper;
import edu.eci.pdsw.samples.entities.Area;
import java.util.List;

import org.apache.ibatis.exceptions.PersistenceException;

/**
 *
 * @author ECI-JACS
 */
public class MyBATISAreaDAO implements AreaDAO {
    
    @Inject
    private AreaMapper areaMapper;
    
    @Override
    public void saveArea(Area area) throws PersistenceException {
        try {
            areaMapper.insertarArea(area);
        } catch (PersistenceException e) {
            throw new PersistenceException("Error al insertar el area " + area.toString(), e);
        }
    }

    @Override
    public Area loadArea(int id) throws PersistenceException {
        try {
            return areaMapper.consultarArea(id);
        } catch (PersistenceException e) {
            throw new PersistenceException("Error al consultar el area " + id, e);
        }
    }

    @Override
    public List<Area> loadAreas() throws PersistenceException {
        try {
            return areaMapper.consultarAreas();
        } catch (PersistenceException e) {
            throw new PersistenceException("Error al consultar las areas.",e);
        }
    }
    
}
