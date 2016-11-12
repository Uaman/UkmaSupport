package com.ukmaSupport.dao.impl;

import com.ukmaSupport.dao.mapper.AuditoriumMapper;
import com.ukmaSupport.models.Auditorium;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.junit.Assert.assertEquals;
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

    @Test
    public void shouldListAll() {
        //give
        //when
        dao.getAll();
        //then
        verify(jdbcTemplate).query(anyString(), any(AuditoriumMapper.class));
    }

    @Test
    public void shouldUpdateAuditorium() {
        //give
        Auditorium auditorium = new Auditorium();
        auditorium.setUserId(ID);
        auditorium.setNumber(EXPECTED_NUMBER);
        auditorium.setId(ID);
        //when
        dao.update(auditorium);
        //then
        verify(jdbcTemplate).update(anyString(), eq(ID), eq(EXPECTED_NUMBER), eq(ID));
    }



}