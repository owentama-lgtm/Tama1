package com.school.cafeteria.dao;

import com.school.cafeteria.model.OrderMessage;
import com.school.cafeteria.util.Database;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class OrderMessageDAO {
    public void addMessage(int orderId, String sender, String message) throws SQLException {
        String sql = "INSERT INTO order_messages (order_id, sender, message) VALUES (?, ?, ?)";
        try (Connection c = Database.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, orderId);
            ps.setString(2, sender);
            ps.setString(3, message);
            ps.executeUpdate();
        }
    }
    public List<OrderMessage> findByOrderId(int orderId) throws SQLException {
        String sql = "SELECT id, order_id, sender, message, created_at FROM order_messages WHERE order_id=? ORDER BY created_at";
        List<OrderMessage> list = new ArrayList<>();
        try (Connection c = Database.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, orderId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    OrderMessage om = new OrderMessage();
                    om.setId(rs.getInt("id"));
                    om.setOrderId(rs.getInt("order_id"));
                    om.setSender(rs.getString("sender"));
                    om.setMessage(rs.getString("message"));
                    om.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
                    list.add(om);
                }
            }
        }
        return list;
    }
}
