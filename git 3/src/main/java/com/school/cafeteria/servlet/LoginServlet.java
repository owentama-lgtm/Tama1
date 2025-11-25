package com.school.cafeteria.servlet;

import com.school.cafeteria.util.Database;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.*;

@WebServlet("/admin/login")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String user = req.getParameter("username"); 
        String pass = req.getParameter("password");
        try (Connection c = Database.getConnection();
             PreparedStatement ps = c.prepareStatement("SELECT * FROM admins WHERE username=? AND password=?")) {
            ps.setString(1, user);
            ps.setString(2, pass);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    HttpSession s = req.getSession();
                    s.setAttribute("adminUser", user);
                    resp.sendRedirect(req.getContextPath() + "/admin/dashboard");
                    return;
                }
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
        req.setAttribute("error", "Invalid credentials");
        req.getRequestDispatcher("/admin/login.jsp").forward(req, resp);
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/admin/login.jsp").forward(req, resp);
    }
}
