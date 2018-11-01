package edu.eci.pdsw.sampleprj.dao;

import java.util.List;

import org.apache.ibatis.exceptions.PersistenceException;

import edu.eci.pdsw.samples.entities.Iniciativa;

public interface IniciativaDAO {
	
	public void save(Iniciativa ini) throws PersistenceException;
	
	public Iniciativa load(int id) throws PersistenceException;
}