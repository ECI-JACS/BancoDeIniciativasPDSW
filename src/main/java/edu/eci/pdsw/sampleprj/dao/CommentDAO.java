package edu.eci.pdsw.sampleprj.dao;

import org.apache.ibatis.exceptions.PersistenceException;

import edu.eci.pdsw.samples.entities.Comment;

/**
*
* @author ECI-FACS
*/
public interface CommentDAO {
    
    public void insertComment(Comment com) throws PersistenceException;
    
}

