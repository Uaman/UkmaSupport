package com.ukmaSupport.dao.impl;

import com.ukmaSupport.dao.interfaces.OrderDao;
import com.ukmaSupport.models.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository("orderDao")
public class OrderDaoImpl implements OrderDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final String GET_ALL_ORDERS = "SELECT orders.id, orders.user_id, orders.assistant_id, orders.workplace_id, orders.title, orders.content, orders.created_at, orders.status FROM orders";

    private static final String GET_ORDER_BY_ID = "SELECT orders.id, orders.user_id, orders.assistant_id, orders.workplace_id, orders.title, orders.content, orders.created_at, orders.status FROM orders WHERE id=?";

    private static final String DELETE_ORDER = "DELETE FROM orders WHERE id=?";

    private static final String INSERT_QUERY = "INSERT INTO orders (user_id, assistant_id, workplace_id, title, content, created_at, status) VALUES(?,?,?,?,?,?,?)";

    private static final String UPDATE_QUERY = "UPDATE orders SET user_id=?, assistant_id=?, workplace_id=?, title=?, content=?, created_at=?, status=? WHERE id=?";

    @Override
    public Order getById(int id) {
        List<Order> users = jdbcTemplate.query(GET_ORDER_BY_ID, new Object[]{id}, rowMapper);
        return users.isEmpty() ? null : users.get(0);
    }

    @Override
    public void createOrUpdate(final Order order) {
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement prepStat;
                if (order.getId() == 0) {
                    prepStat = con.prepareStatement(INSERT_QUERY);
                    prepStat.setDate(6, new java.sql.Date(new java.util.Date().getTime()));
                } else {
                    prepStat = con.prepareStatement(UPDATE_QUERY);
                    prepStat.setInt(8, order.getId());
                    prepStat.setDate(6, new java.sql.Date(order.getCreatedAt().getTime()));
                }
                prepStat.setInt(1, order.getUserId());
                prepStat.setInt(2, order.getAssistantId());
                prepStat.setInt(3, order.getWorkplaceId());
                prepStat.setString(4, order.getTitle());
                prepStat.setString(5, order.getContent());
                prepStat.setString(7, order.getStatus());
                return prepStat;
            }
        });
    }

    @Override
    public void delete(int id) {
        jdbcTemplate.update(DELETE_ORDER, id);
    }

    @Override
    public List<Order> getAll() {
        return jdbcTemplate.query(GET_ALL_ORDERS, rowMapper);
    }

    private static final RowMapper<Order> rowMapper = new RowMapper<Order>() {

        @Override
        public Order mapRow(ResultSet rs, int rowNum) throws SQLException {
            Order order = new Order();
            order.setId(rs.getInt("id"));
            order.setUserId(rs.getInt("user_id"));
            order.setAssistantId(rs.getInt("assistant_id"));
            order.setWorkplaceId(rs.getInt("workplace_id"));
            order.setTitle(rs.getString("title"));
            order.setContent(rs.getString("content"));
            order.setCreatedAt(new java.util.Date(rs.getDate("created_at").getTime()));
            order.setStatus(rs.getString("status"));
            return order;
        }
    };
}
