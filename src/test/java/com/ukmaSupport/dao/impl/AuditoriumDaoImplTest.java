package com.ukmaSupport.dao.impl;

import com.ukmaSupport.dao.interfaces.AuditoriumDao;
import com.ukmaSupport.dao.mapper.AuditoriumMapper;
import com.ukmaSupport.models.Auditorium;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
public class AuditoriumDaoImplTest {

    private static final String EXPECTED_NUMBER = "number";
    private static final int ID = 1;

    @InjectMocks
    AuditoriumDaoImpl dao;

    @Mock
    JdbcTemplate jdbcTemplate;

    @Test
    public void shouldSaveAuditorium() {
        //given
        Auditorium auditorium = new Auditorium();
        auditorium.setNumber(EXPECTED_NUMBER);
        auditorium.setUserId(ID);
        //when
        dao.save(auditorium);
        //then
        verify(jdbcTemplate).update(anyString(), eq(ID), eq(EXPECTED_NUMBER));
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowExceptionWhenAuditoriumIsNull() {
        //given
        Auditorium auditorium = null;
        //when
        dao.save(auditorium);
        //then
        verify(jdbcTemplate).update(anyString(), eq(ID), eq(EXPECTED_NUMBER));
    }

    @Test
    public void shouldReturnObjectForId() {
        //given
        Auditorium auditorium = new Auditorium();
        when(jdbcTemplate.queryForObject(anyString(), (Object[]) anyObject(), any(AuditoriumMapper.class))).thenReturn(auditorium);
        //when
        Auditorium result = dao.getById(ID);
        //then
        verify(jdbcTemplate).queryForObject(any(String.class), (Object[]) anyObject(), any(AuditoriumMapper.class));
        assertEquals(result, auditorium);
    }

    @Test
    public void shouldGetByNumber() {
        //given
        //when
        dao.getByNumber(EXPECTED_NUMBER);
        //then
        verify(jdbcTemplate).queryForObject(anyString(), eq(new Object[]{EXPECTED_NUMBER}), any(AuditoriumMapper.class));
    }

    @Test
    public void shouldDelete() {
        //give
        //when
        dao.delete(ID);
        //then
        verify(jdbcTemplate).update(anyString(), eq(ID));
    }

}