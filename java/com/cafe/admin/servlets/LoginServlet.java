package com.cafe.admin.servlets;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class LoginServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req,HttpServletResponse resp)throws ServletException,IOException{
        req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req,resp);
    }
    protected void doPost(HttpServletRequest req,HttpServletResponse resp)throws ServletException,IOException{
        String u=req.getParameter("username");
        String p=req.getParameter("password");
        if("admin".equals(u) && "admin123".equals(p)){
            req.getSession().setAttribute("admin",true);
            resp.sendRedirect("menu");
        } else {
            req.setAttribute("error","Invalid credentials");
            req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req,resp);
        }
    }
}
