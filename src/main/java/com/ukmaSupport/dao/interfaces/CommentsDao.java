package com.ukmaSupport.dao.interfaces;


import com.ukmaSupport.models.Comment;

import java.util.List;

public interface CommentsDao {

    //read
    List<Comment> getAllComments(int order_id);

    //create
    void createComment(Comment comment);

    //delete
    void deleteComment();

    //update
    void updateComment();

}
