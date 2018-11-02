package edu.eci.pdsw.sampleprj.dao;

import java.util.List;

import org.apache.ibatis.exceptions.PersistenceException;

import edu.eci.pdsw.samples.entities.Iniciative;

public interface IniciativeDAO {
	
	public void saveIniciative(Iniciative ini) throws PersistenceException;
	
	public Iniciative loadIniciative(int id) throws PersistenceException;
}