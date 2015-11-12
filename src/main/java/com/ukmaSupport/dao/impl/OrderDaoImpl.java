package com.ukmaSupport.dao.impl;

import com.ukmaSupport.dao.interfaces.OrderDao;
import com.ukmaSupport.models.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.List;

@Repository("orderDao")
public class OrderDaoImpl implements OrderDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final String GET_ALL_ORDERS = "SELECT orders.id, orders.user_id, orders.assistant_id, workplace.access_num, orders.title, orders.content, orders.created_at, orders.status FROM orders INNER JOIN workplace ON orders.workplace_id=workplace.id";

    private static final String GET_ALL_ORDERS_BY_USER_ID = "SELECT orders.id, orders.user_id, orders.assistant_id, workplace.access_num, orders.title, orders.content, orders.created_at, orders.status FROM orders INNER JOIN workplace ON orders.workplace_id=workplace.id WHERE user_id=?";

    private static final String GET_ALL_ORDERS_BY_USER_ID_STATUS ="SELECT orders.id, orders.user_id, orders.assistant_id, workplace.access_num, orders.title, orders.content, orders.created_at, orders.status FROM orders INNER JOIN workplace ON orders.workplace_id=workplace.id WHERE user_id=? AND status=?";

    private static final String GET_ORDER_BY_ID = "SELECT orders.id, orders.user_id, orders.assistant_id, workplace.access_num, orders.title, orders.content, orders.created_at, orders.status FROM orders INNER JOIN workplace ON orders.workplace_id=workplace.id WHERE id=?";

    private static final String GET_ORDERS_BY_STATUS = "SELECT orders.id, orders.user_id, orders.assistant_id, workplace.access_num, orders.title, orders.content, orders.created_at, orders.status FROM orders INNER JOIN workplace ON orders.workplace_id=workplace.id WHERE status=?";

    private static final String DELETE_ORDER = "DELETE FROM orders WHERE id=?";

    private static final String INSERT_QUERY = "INSERT INTO orders (user_id, assistant_id, workplace_id, title, content, created_at, status) VALUES(?,?,?,?,?,?,?)";

    private static final String UPDATE_QUERY = "UPDATE orders SET user_id=?, assistant_id=?, workplace_id=(SELECT workplace.id FROM workplace WHERE workplace.access_num=?), title=?, content=?, created_at=?, status=? WHERE id=?";

    @Override
    public Order getById(int id) {
        List<Order> users = jdbcTemplate.query(GET_ORDER_BY_ID, new Object[]{id}, rowMapper);
        return users.isEmpty() ? null : users.get(0);
    }

    @Override
    public List<Order> getByStatus(String status) {
        return jdbcTemplate.query(GET_ORDERS_BY_STATUS, new Object[]{status}, rowMapper);
    }

    @Override
    public List<Order> getByUserId(int user_id) {
        return jdbcTemplate.query(GET_ALL_ORDERS_BY_USER_ID, new Object[]{user_id}, rowMapper);
    }
    @Override
    public List<Order> getUserStatus(int user_id,String status) {
        return jdbcTemplate.query(GET_ALL_ORDERS_BY_USER_ID_STATUS, new Object[]{user_id,status}, rowMapper);
    }

    @Override
    public void createOrUpdate(final Order order) {
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement prepStat;
                if (order.getId() == 0) {
                    prepStat = con.prepareStatement(INSERT_QUERY);
                    prepStat.setTimestamp(6, new java.sql.Timestamp(new java.util.Date().getTime()));
                } else {
                    prepStat = con.prepareStatement(UPDATE_QUERY);
                    prepStat.setInt(8, order.getId());
                    prepStat.setTimestamp(6, new java.sql.Timestamp(order.getCreatedAt().getTime()));
                }
                prepStat.setInt(1, order.getUserId());
                prepStat.setInt(2, order.getAssistantId());
                prepStat.setInt(3, order.getWorkplace_id());
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
            order.setWorkplace_access_num(rs.getString("access_num"));
            order.setTitle(rs.getString("title"));
            order.setContent(rs.getString("content"));
            order.setCreatedAt((rs.getTimestamp("created_at")));
            order.setStatus(rs.getString("status"));
            return order;
        }
    };
}
