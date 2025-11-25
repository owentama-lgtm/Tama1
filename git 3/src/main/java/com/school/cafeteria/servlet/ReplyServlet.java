package com.school.cafeteria.servlet;

import com.school.cafeteria.dao.OrderMessageDAO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/admin/reply")
public class ReplyServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession s = req.getSession(false);
        if (s == null || s.getAttribute("adminUser") == null) {
            resp.sendRedirect(req.getContextPath() + "/admin/login.jsp"); return;
        }
        String message = req.getParameter("message"); 
        int orderId = Integer.parseInt(req.getParameter("orderId"));
        try {
            OrderMessageDAO dao = new OrderMessageDAO();
            dao.addMessage(orderId, "ADMIN", message);
        } catch (Exception e) {
            throw new ServletException(e);
        }
        // After replying, redirect back to dashboard (messages visible there)
        resp.sendRedirect(req.getContextPath() + "/admin/dashboard");
    }
}
