package edu.eci.pdsw.sampleprj.dao;

import java.util.List;

import org.apache.ibatis.exceptions.PersistenceException;

import edu.eci.pdsw.samples.entities.Initiative;

public interface InitiativeDAO {
	
	public void saveInitiative(Initiative ini) throws PersistenceException;
	
	public Initiative loadInitiative(int id) throws PersistenceException;
}