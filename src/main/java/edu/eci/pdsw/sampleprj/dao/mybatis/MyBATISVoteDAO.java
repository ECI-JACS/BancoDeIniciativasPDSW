package edu.eci.pdsw.sampleprj.dao.mybatis;

import org.apache.ibatis.exceptions.PersistenceException;

import com.google.inject.Inject;

import edu.eci.pdsw.sampleprj.dao.VoteDAO;
import edu.eci.pdsw.sampleprj.dao.mybatis.mappers.VoteMapper;
import edu.eci.pdsw.samples.entities.Vote;

public class MyBATISVoteDAO implements VoteDAO {

    @Inject
    private VoteMapper voteMapper;

    @Override
    public void saveVote(Vote voto) throws PersistenceException {
        try {
            voteMapper.votar(voto);
        } catch (PersistenceException e) {
            throw new PersistenceException("Error al votar por la iniciativa " + voto.getIniciativaId(), e);
        }
    }

    @Override
    public int loadVoteId() throws PersistenceException {
        try {
            return voteMapper.consultarIdVoto();
        } catch (PersistenceException e) {
            throw new PersistenceException("Error al consultar el máximo id que tomará un voto", e);
        }
    }

    @Override
    public void deleteVote(String userEmail, int iniciativaId) throws PersistenceException {
        try {
            voteMapper.quitarVoto(userEmail, iniciativaId);
        } catch (PersistenceException e) {
            throw new PersistenceException("Error al quitar el voto de la iniciativa " + iniciativaId, e);
        }
    }
    
}