package edu.eci.pdsw.sampleprj.dao;

import java.util.List;
import java.sql.Date;

import org.apache.ibatis.exceptions.PersistenceException;

import edu.eci.pdsw.samples.entities.Initiative;
import edu.eci.pdsw.samples.entities.InitiativeStatus;

public interface InitiativeDAO {

    public void saveInitiative(Initiative ini) throws PersistenceException;

    public Initiative loadInitiative(int id) throws PersistenceException;

    public void saveInitiativeStatus(InitiativeStatus iniSta) throws PersistenceException;

    public InitiativeStatus loadInitiativesStatus(int id) throws PersistenceException;
    
    public void updateInitiativeStatus(int id,int iniStat) throws PersistenceException;
    
    public List<Initiative> loadInitiativeForSearch(String palabrasClave, String proponente, Date fechaPropuesta, int dependencia, int estado) throws PersistenceException;

    public int loadInitiativeId() throws PersistenceException;

    public List<Initiative> loadInitiatives() throws PersistenceException;

    public List<InitiativeStatus> loadAllInitiativesStatus() throws PersistenceException;
}
