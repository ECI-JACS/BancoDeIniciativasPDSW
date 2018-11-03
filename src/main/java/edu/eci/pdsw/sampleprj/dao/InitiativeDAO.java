package edu.eci.pdsw.sampleprj.dao;

import org.apache.ibatis.exceptions.PersistenceException;

import edu.eci.pdsw.samples.entities.Initiative;
import edu.eci.pdsw.samples.entities.InitiativeStatus;

public interface InitiativeDAO {

    public void saveInitiative(Initiative ini) throws PersistenceException;

    public Initiative loadInitiative(int id) throws PersistenceException;

    public void saveInitiativeStatus(InitiativeStatus iniSta) throws PersistenceException;

    public InitiativeStatus loadInitiativesStatus(int id) throws PersistenceException;
}
