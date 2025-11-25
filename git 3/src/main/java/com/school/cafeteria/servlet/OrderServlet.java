package com.school.cafeteria.servlet;

import com.school.cafeteria.dao.OrderDAO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/admin/order")
public class OrderServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession s = req.getSession(false);
        if (s == null || s.getAttribute("adminUser") == null) { resp.sendRedirect(req.getContextPath() + "/admin/login.jsp"); return; }
        String action = req.getParameter("action");
        OrderDAO dao = new OrderDAO();
        try {
            if ("complete".equals(action)) {
                int id = Integer.parseInt(req.getParameter("id"));
                dao.setStatus(id, "COMPLETED");
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
        resp.sendRedirect(req.getContextPath() + "/admin/dashboard");
    }
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException { doPost(req, resp); }
}
