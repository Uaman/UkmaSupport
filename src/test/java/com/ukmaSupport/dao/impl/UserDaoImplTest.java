package com.ukmaSupport.dao.impl;

import com.ukmaSupport.models.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class UserDaoImplTest {

    private static final int ID = 1;
    private static final String AUDITORIUM = "auditorium";
    private static final String EMAIL = "email";
    private static final String ROLE = "role";
    private static final String STATUS = "status";

    @InjectMocks
    UserDaoImpl dao;

    @Mock
    JdbcTemplate jdbcTemplate;

    @Test
    public void shouldGetById() {
        //given
        User user = new User();
        when(jdbcTemplate.queryForObject(anyString(), eq(new Object[]{ID}), any(RowMapper.class))).thenReturn(user);
        //when
        User result = dao.getById(ID);
        //then
        verify(jdbcTemplate).queryForObject(anyString(), eq(new Object[]{ID}), any(RowMapper.class));
        assertEquals(result, user);
    }

    @Test
    public void shouldDelete() {
        //given
        //when
        dao.delete(ID);
        //then
        verify(jdbcTemplate).update(anyString(), eq(ID));
    }

    @Test
    public void shouldSaveOrUpdate() {
        //given
        User user = new User();
        //when
        dao.saveOrUpdate(user);
        //then
        verify(jdbcTemplate).update(any(PreparedStatementCreator.class));
    }

    @Test
    public void shouldGetResponsibleAssistant() {
        //given
        //when
        dao.getResponsibleAssistant(AUDITORIUM);
        //then
        verify(jdbcTemplate).queryForObject(anyString(), eq(new Object[]{AUDITORIUM}), any(RowMapper.class));
    }

    @Test
    public void shouldGetByEmail() {
        //given
        //when
        dao.getByEmail(EMAIL);
        //then
        verify(jdbcTemplate).queryForObject(anyString(), eq(new Object[]{EMAIL}), any(RowMapper.class));
    }

    @Test
    public void shouldGetByRole() {
        //given
        //when
        dao.getByRole(ROLE);
        //then
        verify(jdbcTemplate).query(anyString(), eq(new Object[]{ROLE}), any(RowMapper.class));
    }

    @Test
    public void shouldGetByStatus() {
        //given
        //when
        dao.getByStatus(STATUS);
        //then
        verify(jdbcTemplate).query(anyString(), eq(new Object[]{STATUS}), any(RowMapper.class));
    }

}
