package com.cafe.admin.servlets;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import com.cafe.admin.dao.MenuDAO;

public class MenuServlet extends HttpServlet {
    MenuDAO dao=new MenuDAO();
    protected void doGet(HttpServletRequest req,HttpServletResponse resp)throws ServletException,IOException{
        req.setAttribute("items",dao.getAll());
        req.getRequestDispatcher("/WEB-INF/views/menu.jsp").forward(req,resp);
    }
    protected void doPost(HttpServletRequest req,HttpServletResponse resp)throws ServletException,IOException{
        String name=req.getParameter("name");
        double price=Double.parseDouble(req.getParameter("price"));
        dao.add(name,price);
        resp.sendRedirect("menu");
    }
}
