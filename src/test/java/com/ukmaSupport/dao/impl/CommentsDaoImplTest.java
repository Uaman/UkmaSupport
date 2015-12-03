package com.ukmaSupport.dao.impl;

import com.ukmaSupport.models.Comment;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;

import static org.mockito.Matchers.*;
import static org.mockito.Mockito.verify;


@RunWith(MockitoJUnitRunner.class)
public class CommentsDaoImplTest {

    private static final String EXPECTED_NUMBER = "number";
    private static final int ID = 1;

    @InjectMocks
    CommentsDaoImpl dao;

    @Mock
    JdbcTemplate jdbcTemplate;

    @Test
    public void shouldGetAllComments(){
        //given
        //when
        dao.getAllComments(ID);
        //then
        verify(jdbcTemplate).query(anyString(), eq(new Object[]{ID}), any(RowMapper.class));
    }

    @Test
    public void shouldCreateComment(){
        //given
        final Comment comment = null;
        //when
        dao.createComment(comment);
        //then
        verify(jdbcTemplate).update(any(PreparedStatementCreator.class));
    }
}