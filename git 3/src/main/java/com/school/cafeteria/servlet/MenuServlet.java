package com.school.cafeteria.servlet;

import com.school.cafeteria.dao.MenuItemDAO;
import com.school.cafeteria.model.MenuItem;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/admin/menu")
public class MenuServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession s = req.getSession(false);
        if (s == null || s.getAttribute("adminUser") == null) { resp.sendRedirect(req.getContextPath() + "/admin/login.jsp"); return; }
        String action = req.getParameter("action");
        MenuItemDAO dao = new MenuItemDAO();
        try {
            if ("add".equals(action)) {
                String name = req.getParameter("name"); String desc = req.getParameter("description"); double price = Double.parseDouble(req.getParameter("price")); boolean avail = req.getParameter("available") != null;
                dao.add(new MenuItem(name, desc, price, avail));
            } else if ("update".equals(action)) {
                int id = Integer.parseInt(req.getParameter("id"));
                MenuItem m = dao.findById(id);
                if (m != null) {
                    m.setName(req.getParameter("name"));
                    m.setDescription(req.getParameter("description"));
                    m.setPrice(Double.parseDouble(req.getParameter("price")));
                    m.setAvailable(req.getParameter("available") != null);
                    dao.update(m);
                }
            } else if ("delete".equals(action)) {
                int id = Integer.parseInt(req.getParameter("id"));
                dao.delete(id);
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
        resp.sendRedirect(req.getContextPath() + "/admin/dashboard");
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
