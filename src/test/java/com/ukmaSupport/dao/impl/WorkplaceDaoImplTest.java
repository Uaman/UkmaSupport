package com.ukmaSupport.dao.impl;


import com.ukmaSupport.models.Workplace;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import com.ukmaSupport.dao.mapper.WorkplaceMapper;

@RunWith(MockitoJUnitRunner.class)
public class WorkplaceDaoImplTest {

    private static final int EXPECTED_NUMBER = 1;
    private static final int ID = 2;
    private static final String AUDITORIUM_NAME = "name";
    private static final int AUDITORIUM_ID = 3;
    private static final int ACESS_NUMBER = 4;

    @InjectMocks
    WorkplaceDaoImpl dao;

    @Mock
    JdbcTemplate jdbcTemplate;

    @Test
    public void shouldSaveWorkplace() {
        //given
        Workplace workplace = new Workplace();
        workplace.setAccessNumber(EXPECTED_NUMBER);
        workplace.setAuditoriumId(ID);
        //when
        dao.save(workplace);
        //then
        verify(jdbcTemplate).update(anyString(), eq(ID), eq(EXPECTED_NUMBER));
    }

    @Test
    public void shouldGetByID() {
        //given
        Workplace workplace = new Workplace();
        when(jdbcTemplate.queryForObject(anyString(), eq(new Object[]{ID}), any(WorkplaceMapper.class))).thenReturn(workplace);
        //when
        Workplace result = dao.getById(ID);
        //then
        verify(jdbcTemplate).queryForObject(anyString(), eq(new Object[]{ID}), any(WorkplaceMapper.class));
        assertEquals(result, workplace);
    }

    @Test
    public void shouldGetByNumber() {
        //given
        //when
        dao.getByNumber(EXPECTED_NUMBER);
        //then
        verify(jdbcTemplate).queryForObject(anyString(), eq(new Object[]{EXPECTED_NUMBER}), any(WorkplaceMapper.class));
    }

    @Test
    public void shouldGetByAuditoriumName() {
        //given
        //when
        dao.getByAuditoriumName(AUDITORIUM_NAME);
        //then
        verify(jdbcTemplate).query(anyString(), eq(new Object[]{AUDITORIUM_NAME}), any(WorkplaceMapper.class));
    }

    @Test
    public void shouldUpdate() {
        //given
        Workplace workplace = new Workplace();
        workplace.setId(ID);
        workplace.setAccessNumber(ACESS_NUMBER);
        workplace.setAuditoriumId(AUDITORIUM_ID);
        //when
        dao.update(workplace);
        //then
        verify(jdbcTemplate).update(anyString(), eq(AUDITORIUM_ID), eq(ACESS_NUMBER), eq(ID));
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
    public void shouldGetAll() {
        //given
        //when
        dao.getAll();
        //then
        verify(jdbcTemplate).query(anyString(), any(WorkplaceMapper.class));
    }
}