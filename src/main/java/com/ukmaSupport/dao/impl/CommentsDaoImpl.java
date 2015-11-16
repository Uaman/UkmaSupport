package com.ukmaSupport.dao.impl;

import com.ukmaSupport.dao.interfaces.CommentsDao;
import com.ukmaSupport.models.Comment;
import com.ukmaSupport.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository("commentsDao")
public class CommentsDaoImpl implements CommentsDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private String GET_ALL_COMMENTS_FOR_ORDER = "SELECT users.id_user, users.data_entry, users.email, users.first_name, users.last_name, users.status_account, user_roles.role, comment.id, comment.order_id, comment.content, comment.time " +
            "FROM comment " +
            "INNER JOIN users ON comment.user_id=users.id_user " +
            "INNER JOIN user_roles ON users.user_roleid=user_roles.id " +
            "WHERE comment.order_id = ? " +
            "ORDER BY comment.time";

    @Override
    public List<Comment> getAllComments(int order_id) {
        return jdbcTemplate.query(GET_ALL_COMMENTS_FOR_ORDER, new Object[]{order_id},rowMapper);
    }

    @Override
    public void createComment(Comment comment) {

    }

    @Override
    public void deleteComment() {

    }

    @Override
    public void updateComment() {

    }

    private static final RowMapper<Comment> rowMapper = new RowMapper<Comment>() {

        @Override
        public Comment mapRow(ResultSet rs, int rowNum) throws SQLException {
            Comment comment = new Comment();
            comment.setId(rs.getInt("id"));
            comment.setContent(rs.getString("content"));
            comment.setAuthor(new User());
            comment.getAuthor().setId(rs.getInt("id_user"));
            comment.getAuthor().setFirstName(rs.getString("first_name"));
            comment.getAuthor().setLastName(rs.getString("last_name"));
            comment.getAuthor().setEmail(rs.getString("email"));
            comment.getAuthor().setRole(rs.getString("role"));
            comment.getAuthor().setAccountStatus(rs.getString("status_account"));
            comment.getAuthor().setDateOfEntry(new java.util.Date(rs.getDate("data_entry").getTime()));
            comment.setOrderId(rs.getInt("order_id"));
            comment.setTime(rs.getLong("time"));
            return comment;
        }
    };
}
