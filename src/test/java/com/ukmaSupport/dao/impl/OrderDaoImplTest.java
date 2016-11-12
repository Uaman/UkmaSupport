package com.ukmaSupport.dao.impl;

import com.ukmaSupport.models.Order;
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
public class OrderDaoImplTest {

    private static final String EXPECTED_NUMBER = "number";
    private static final int ID = 1;

    @InjectMocks
    OrderDaoImpl dao;

    @Mock
    JdbcTemplate jdbcTemplate;

    @Test
    public void shouldGetById()  {
        //given
        //when
        dao.getById(ID);
        //then
        verify(jdbcTemplate).queryForObject(anyString(), eq(new Object[]{ID}), any(RowMapper.class));
    }

    @Test
    public void shouldGetByTime() {
        //given
        //when
        dao.getByTime(EXPECTED_NUMBER);
        //then
        verify(jdbcTemplate).queryForObject(anyString(), eq(new Object[]{EXPECTED_NUMBER}), any(RowMapper.class));
    }

    @Test
    public void shouldGetByAuditoriumNumber() {
        //given
        //when
        dao.getByAuditoriumNumber(EXPECTED_NUMBER);
        //then
        verify(jdbcTemplate).query(anyString(), eq(new Object[]{EXPECTED_NUMBER}), any(RowMapper.class));
    }

    @Test
    public void shouldGetByWorkplaceAcessNum()  {
        //given
        //when
        dao.getByWorkplaceAcessNum(ID);
        //then
        verify(jdbcTemplate).query(anyString(), eq(new Object[]{ID}), any(RowMapper.class));
    }

    @Test
    public void shouldGetByUserId()  {
        //given
        //when
        dao.getByUserId(ID);
        //then
        verify(jdbcTemplate).query(anyString(), eq(new Object[]{ID}), any(RowMapper.class));
    }

    @Test
    public void shouldGetByUserIdAndId()  {
        //given
        //when
        dao.getByUserIdAndId(ID,ID);
        //then
        verify(jdbcTemplate).queryForObject(anyString(), eq(new Object[]{ID, ID}), any(RowMapper.class));
    }

    @Test
    public void shouldCreateOrUpdate() {
        //given
        final Order order = null;
        //when
        dao.createOrUpdate(order);
        //then
        verify(jdbcTemplate).update(any(PreparedStatementCreator.class));
    }
}