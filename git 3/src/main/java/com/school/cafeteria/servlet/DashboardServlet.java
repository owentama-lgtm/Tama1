package com.school.cafeteria.servlet;

import com.school.cafeteria.dao.MenuItemDAO;
import com.school.cafeteria.dao.OrderDAO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/admin/dashboard")
public class DashboardServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession s = req.getSession(false);
        if (s == null || s.getAttribute("adminUser") == null) {
            resp.sendRedirect(req.getContextPath() + "/admin/login.jsp"); return;
        }
        try {
            MenuItemDAO mdao = new MenuItemDAO();
            OrderDAO odao = new OrderDAO();
            req.setAttribute("menuList", mdao.listAll());
            req.setAttribute("orders", odao.listAll());
        } catch (Exception e) {
            throw new ServletException(e);
        }
        req.getRequestDispatcher("/admin/dashboard.jsp").forward(req, resp);
    }
}
