package com.ukmaSupport.dao.impl;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.Types;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class RoleDaoImplTest {

    private static final int ID = 1;
    private static final String ROLE = "user";


    @InjectMocks
    RoleDaoImpl dao;

    @Mock
    JdbcTemplate jdbcTemplate;

    @Test
    public void shouldGetById() {
        //given
        //when
        dao.getById(ID);
        //then
        verify(jdbcTemplate).queryForObject(anyString(), eq(new Object[]{ID}), any(RowMapper.class));
    }

    @Test
    public void shouldSave() {
        //given
        //when
        dao.save(ROLE);
        //then
        verify(jdbcTemplate).update(anyString(), eq(new Object[]{ROLE}), eq(new Object[]{Types.VARCHAR}));
    }

    @Test
    public void shouldDelete() {
        //given
        //when
        dao.delete(ID);
        //then
        verify(jdbcTemplate).update(anyString(), eq(ID));
    }

}
