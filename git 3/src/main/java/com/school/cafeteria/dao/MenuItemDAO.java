package com.school.cafeteria.dao;

import com.school.cafeteria.model.MenuItem;
import com.school.cafeteria.util.Database;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MenuItemDAO {
    public void add(MenuItem item) throws SQLException {
        String sql = "INSERT INTO menu_items (name, description, price, available) VALUES (?, ?, ?, ?)";
        try (Connection c = Database.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, item.getName());
            ps.setString(2, item.getDescription());
            ps.setDouble(3, item.getPrice());
            ps.setBoolean(4, item.isAvailable());
            ps.executeUpdate();
        }
    }
    public void update(MenuItem item) throws SQLException {
        String sql = "UPDATE menu_items SET name=?, description=?, price=?, available=? WHERE id=?";
        try (Connection c = Database.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, item.getName());
            ps.setString(2, item.getDescription());
            ps.setDouble(3, item.getPrice());
            ps.setBoolean(4, item.isAvailable());
            ps.setInt(5, item.getId());
            ps.executeUpdate();
        }
    }
    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM menu_items WHERE id=?";
        try (Connection c = Database.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }
    public MenuItem findById(int id) throws SQLException {
        String sql = "SELECT * FROM menu_items WHERE id=?";
        try (Connection c = Database.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new MenuItem(rs.getInt("id"), rs.getString("name"), rs.getString("description"), rs.getDouble("price"), rs.getBoolean("available"));
                }
            }
        }
        return null;
    }
    public List<MenuItem> listAll() throws SQLException {
        String sql = "SELECT * FROM menu_items ORDER BY id";
        List<MenuItem> list = new ArrayList<>();
        try (Connection c = Database.getConnection(); PreparedStatement ps = c.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(new MenuItem(rs.getInt("id"), rs.getString("name"), rs.getString("description"), rs.getDouble("price"), rs.getBoolean("available")));
            }
        }
        return list;
    }
}
