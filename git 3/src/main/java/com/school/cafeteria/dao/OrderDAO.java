package com.school.cafeteria.dao;

import com.school.cafeteria.model.Order;
import com.school.cafeteria.util.Database;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO {
    public List<Order> listAll() throws SQLException {
        String sql = "SELECT id, student_name, student_contact, status, created_at FROM orders ORDER BY created_at DESC";
        List<Order> list = new ArrayList<>();
        try (Connection c = Database.getConnection(); PreparedStatement ps = c.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Order o = new Order();
                o.setId(rs.getInt("id"));
                o.setStudentName(rs.getString("student_name"));
                // set contact via setter if Order model updated to include it
                try { o.getClass().getMethod("setStudentContact", String.class).invoke(o, rs.getString("student_contact")); } catch (Exception ignore) {}
                o.setStatus(rs.getString("status"));
                o.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
                list.add(o);
            }
        }
        return list;
    }
    public void setStatus(int orderId, String status) throws SQLException {
        String sql = "UPDATE orders SET status=? WHERE id=?";
        try (Connection c = Database.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, status);
            ps.setInt(2, orderId);
            ps.executeUpdate();
        }
    }
}
