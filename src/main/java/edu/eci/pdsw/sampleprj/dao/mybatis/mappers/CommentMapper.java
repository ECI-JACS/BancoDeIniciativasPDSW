package edu.eci.pdsw.sampleprj.dao.mybatis.mappers;

import java.sql.Date;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import edu.eci.pdsw.samples.entities.Comment;

/**
 *
 * @author ECI-JACS
 */
public interface CommentMapper {
    
    public void insertarComentario(@Param("com") Comment com);
    
}