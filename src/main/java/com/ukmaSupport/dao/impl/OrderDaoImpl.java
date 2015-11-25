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
    private static final String GET_ALL_ORDERS = "SELECT orders.id, orders.workplace_id, orders.user_id, orders.assistant_id, workplace.auditorium_id, workplace.access_num, orders.title, orders.content, orders.created_at, orders.status, auditorium.number, concat(users.last_name, '') AS assist FROM (orders INNER JOIN workplace ON orders.workplace_id=workplace.id) LEFT JOIN auditorium ON workplace.auditorium_id=auditorium.id LEFT JOIN users ON users.id_user=orders.assistant_id";

    private static final String GET_ALL_ORDERS_BY_USER_ID = "SELECT orders.id, orders.workplace_id, orders.user_id, orders.assistant_id, workplace.auditorium_id, workplace.access_num, orders.title, orders.content, orders.created_at, orders.status, auditorium.number, users.last_name AS assist FROM (orders INNER JOIN workplace ON orders.workplace_id=workplace.id) LEFT JOIN auditorium ON workplace.auditorium_id=auditorium.id LEFT JOIN users ON users.id_user=orders.assistant_id WHERE orders.user_id=?";

    private static final String GET_ALL_ORDERS_BY_AUDITORIUM_NUMBER = "SELECT orders.id, orders.workplace_id, orders.user_id, orders.assistant_id, workplace.auditorium_id, workplace.access_num, orders.title, orders.content, orders.created_at, orders.status, auditorium.number, users.last_name AS assist FROM (orders INNER JOIN workplace ON orders.workplace_id=workplace.id) LEFT JOIN auditorium ON workplace.auditorium_id=auditorium.id LEFT JOIN users ON users.id_user=orders.assistant_id WHERE auditorium.number=?";

    private static final String GET_ALL_ORDERS_BY_WORKPLACE_ACCESS_NUM = "SELECT orders.id, orders.workplace_id, orders.user_id, orders.assistant_id, workplace.auditorium_id, workplace.access_num, orders.title, orders.content, orders.created_at, orders.status, auditorium.number, users.last_name AS assist FROM (orders INNER JOIN workplace ON orders.workplace_id=workplace.id) LEFT JOIN auditorium ON workplace.auditorium_id=auditorium.id LEFT JOIN users ON users.id_user=orders.assistant_id WHERE workplace.access_num=?";

    private static final String GET_ALL_ORDERS_BY_USER_ID_AND_ID = "SELECT orders.id, orders.workplace_id, orders.user_id, orders.assistant_id, workplace.auditorium_id, workplace.access_num, orders.title, orders.content, orders.created_at, orders.status, auditorium.number, users.last_name AS assist FROM (orders INNER JOIN workplace ON orders.workplace_id=workplace.id) LEFT JOIN auditorium ON workplace.auditorium_id=auditorium.id LEFT JOIN users ON users.id_user=orders.assistant_id WHERE orders.id=? AND orders.user_id=?";

    private static final String GET_ALL_ORDERS_BY_USER_ID_STATUS = "SELECT orders.id, orders.workplace_id, orders.user_id, orders.assistant_id, workplace.auditorium_id, workplace.access_num, orders.title, orders.content, orders.created_at, orders.status, auditorium.number, users.last_name AS assist FROM (orders INNER JOIN workplace ON orders.workplace_id=workplace.id) LEFT JOIN auditorium ON workplace.auditorium_id=auditorium.id LEFT JOIN users ON users.id_user=orders.assistant_id WHERE orders.assistant_id=? AND orders.status=?";

    private static final String GET_ORDER_BY_ID = "SELECT orders.id, orders.workplace_id, orders.user_id, orders.assistant_id, workplace.auditorium_id, workplace.access_num, orders.title, orders.content, orders.created_at, orders.status, auditorium.number, users.last_name AS assist FROM (orders INNER JOIN workplace ON orders.workplace_id=workplace.id) LEFT JOIN auditorium ON workplace.auditorium_id=auditorium.id LEFT JOIN users ON users.id_user=orders.assistant_id  WHERE orders.id=?";

    private static final String GET_ORDERS_BY_STATUS = "SELECT orders.id, orders.user_id, orders.assistant_id, workplace.auditorium_id,workplace.access_num, orders.title, orders.content, orders.created_at, orders.status FROM orders INNER JOIN workplace ON orders.workplace_id=workplace.id WHERE status=?";

    private static final String GET_ALL_ASSIST_ORDERS = "SELECT orders.id, orders.workplace_id, orders.user_id, orders.assistant_id, workplace.auditorium_id, workplace.access_num, orders.title, orders.content, orders.created_at, orders.status, auditorium.number, users.last_name AS assist FROM (orders INNER JOIN workplace ON orders.workplace_id=workplace.id) LEFT JOIN auditorium ON workplace.auditorium_id=auditorium.id LEFT JOIN users ON users.id_user=orders.assistant_id WHERE assistant_id=?";

    private static final String GET_ALL_ORDERS_BY_ASSIST_AND_STATUS = "SELECT COUNT (orders.status) FROM (orders INNER JOIN workplace ON orders.workplace_id=workplace.id) LEFT JOIN auditorium ON workplace.auditorium_id=auditorium.id LEFT JOIN users ON users.id_user=orders.assistant_id WHERE orders.assistant_id=? AND orders.status=? ";

    private static final String DELETE_ORDER = "DELETE FROM orders WHERE id=?";

    private static final String INSERT_QUERY = "INSERT INTO orders (user_id, assistant_id, workplace_id, title, content, created_at, status) VALUES(?,?,?,?,?,?,?)";

    private static final String UPDATE_QUERY = "UPDATE orders SET user_id=?, assistant_id=?, workplace_id=?, title=?, content=?, created_at=?, status=? WHERE id=?";

    private static final String UPDATE = "UPDATE orders SET title=?, content=?, created_at=? WHERE id=?";

    @Override
    public Order getById(int id) {
        return jdbcTemplate.queryForObject(GET_ORDER_BY_ID, new Object[]{id}, rowMapper);
    }

    @Override
    public List<Order> getByStatus(String status) {
        return jdbcTemplate.query(GET_ORDERS_BY_STATUS, new Object[]{status}, rowMapper);
    }
    @Override
    public int getCountOrderByAssistant(int assistant_id,String status) {
        int total= jdbcTemplate.queryForObject(GET_ALL_ORDERS_BY_ASSIST_AND_STATUS, new Object[]{assistant_id, status}, Integer.class);
        return total;
    }

    @Override
    public List<Order> getByAuditoriumNumber(String number) {
        return jdbcTemplate.query(GET_ALL_ORDERS_BY_AUDITORIUM_NUMBER, new Object[]{number}, rowMapper);
    }

    @Override
    public List<Order> getByWorkplaceAcessNum(int access_num) {
        return jdbcTemplate.query(GET_ALL_ORDERS_BY_WORKPLACE_ACCESS_NUM, new Object[]{access_num}, rowMapper);
    }
    @Override
    public List<Order> getByUserId(int user_id) {
        return jdbcTemplate.query(GET_ALL_ORDERS_BY_USER_ID, new Object[]{user_id}, rowMapper);
    }
    public Order getByUserIdAndId(int user_id, int id) {
        return jdbcTemplate.queryForObject(GET_ALL_ORDERS_BY_USER_ID_AND_ID, new Object[]{id, user_id}, rowMapper);
    }

    @Override
    public List<Order> getUserStatus(int user_id, String status) {
        return jdbcTemplate.query(GET_ALL_ORDERS_BY_USER_ID_STATUS, new Object[]{user_id, status}, rowMapper);
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
    public void update(Order order) {
        jdbcTemplate.update(UPDATE, order.getTitle(), order.getContent(), order.getCreatedAt(), order.getId());
    }

    @Override
    public void delete(int id) {
        jdbcTemplate.update(DELETE_ORDER, id);
    }

    @Override
    public List<Order> getAll() {
        return jdbcTemplate.query(GET_ALL_ORDERS, rowMapper);
    }

    @Override
    public List<Order> getAllAssistOrders(int assistant_id) {
        return jdbcTemplate.query(GET_ALL_ASSIST_ORDERS, new Object[]{assistant_id}, rowMapper);
    }

    @Override
    public List<Order> getByAssistAndStatus(int assistant_id, String status) {
        return jdbcTemplate.query(GET_ALL_ORDERS_BY_ASSIST_AND_STATUS, new Object[]{assistant_id, status}, rowMapper);
    }

    private static final RowMapper<Order> rowMapper = new RowMapper<Order>() {

        @Override
        public Order mapRow(ResultSet rs, int rowNum) throws SQLException {
            Order order = new Order();
            order.setId(rs.getInt("id"));
            order.setAssistantLastName(rs.getString("assist"));
            order.setUserId(rs.getInt("user_id"));
            order.setAssistantId(rs.getInt("assistant_id"));
            order.setWorkplace_id(rs.getInt("workplace_id"));
            order.setWorkplace_access_num(rs.getString("access_num"));
            order.setAuditorium(rs.getString("number"));
            order.setAuditoriumId(rs.getInt("auditorium_id"));
            order.setTitle(rs.getString("title"));
            order.setContent(rs.getString("content"));
            order.setCreatedAt((rs.getTimestamp("created_at")));
            order.setStatus(rs.getString("status"));
            return order;
        }
    };
}
