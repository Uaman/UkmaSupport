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

import java.util.Arrays;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
public class AuditoriumDaoImplTest {

    private static final String EXPECTED_NUMBER = "number";
    private static final int EXPECTED_USER_ID = 1;
    @InjectMocks
    AuditoriumDaoImpl dao;

    @Mock
    JdbcTemplate jdbcTemplate;

    @Test
    public void shouldSaveAuditorium(){
        //given
        Auditorium auditorium = new Auditorium();
        auditorium.setNumber(EXPECTED_NUMBER);
        auditorium.setUserId(EXPECTED_USER_ID);
        //when
        dao.save(auditorium);
        //then
        verify(jdbcTemplate).update(any(String.class), eq(EXPECTED_USER_ID) ,eq(EXPECTED_NUMBER));
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowExceptionWhenAuditoriumIsNull(){
        //given
        Auditorium auditorium = null;
        //when
        dao.save(auditorium);
        //then
        verify(jdbcTemplate).update(any(String.class), eq(EXPECTED_USER_ID) ,eq(EXPECTED_NUMBER));
    }

}