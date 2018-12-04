package edu.eci.pdsw.sampleprj.dao.mybatis;

import org.apache.ibatis.exceptions.PersistenceException;

import com.google.inject.Inject;

import edu.eci.pdsw.sampleprj.dao.CommentDAO;
import edu.eci.pdsw.sampleprj.dao.mybatis.mappers.CommentMapper;
import edu.eci.pdsw.samples.entities.Comment;

public class MyBATISCommentDAO implements CommentDAO {

    @Inject
    private CommentMapper commentMapper;

    @Override
    public void insertComment(Comment com) throws PersistenceException {
        try {
            commentMapper.insertarComentario(com);
        } catch (PersistenceException e) {
            throw new PersistenceException("Error al insertar el comentario " + com.toString(), e);
        }
    }

}
