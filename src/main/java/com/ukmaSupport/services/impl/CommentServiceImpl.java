package com.ukmaSupport.services.impl;

import com.ukmaSupport.dao.interfaces.CommentsDao;
import com.ukmaSupport.models.Comment;
import com.ukmaSupport.services.interfaces.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("commentService")
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentsDao commentsDao;

    @Override
    public List<Comment> getAllComments(int order_id) {
        return commentsDao.getAllComments(order_id);
    }

    @Override
    public void createComment(Comment comment) {
        commentsDao.createComment(comment);
    }

    @Override
    public void deleteComment() {

    }

    @Override
    public void updateComment() {

    }
}
