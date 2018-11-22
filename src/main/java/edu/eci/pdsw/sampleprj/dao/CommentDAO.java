package edu.eci.pdsw.sampleprj.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.exceptions.PersistenceException;

import edu.eci.pdsw.samples.entities.Comment;

/**
*
* @author ECI-FACS
*/
public interface CommentDAO {
    
    public void insertComment(Comment com) throws PersistenceException;
    
}

