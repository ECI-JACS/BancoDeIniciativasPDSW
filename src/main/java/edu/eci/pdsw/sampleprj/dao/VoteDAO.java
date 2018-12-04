package edu.eci.pdsw.sampleprj.dao;

import org.apache.ibatis.exceptions.PersistenceException;

import edu.eci.pdsw.samples.entities.Vote;

/**
*
* @author ECI-FACS
*/
public interface VoteDAO {
    
    public void saveVote(Vote voto) throws PersistenceException;
    
    public int loadVoteId() throws PersistenceException;
    
    public void deleteVote(String userEmail, int iniciativaId) throws PersistenceException;
    
}