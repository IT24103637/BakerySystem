package com.BakeryOrder.servlet;

import com.BakeryOrder.dao.UserDAO;
import com.BakeryOrder.dao.AdminDAO;
import com.BakeryOrder.dto.UserDTO;
import com.BakeryOrder.dto.AdminDTO;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        String userPath = getServletContext().getRealPath("/") + "users.txt";
        String adminPath = getServletContext().getRealPath("/") + "admins.txt";

        UserDAO userDAO = new UserDAO(userPath);
        AdminDAO adminDAO = new AdminDAO(adminPath);

        HttpSession session = request.getSession();

        // Check user
        UserDTO user = userDAO.getUserByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            session.setAttribute("username", user.getUsername());
            session.setAttribute("user", user.getFirstname());
            session.setAttribute("role", "user");
            response.sendRedirect("index.jsp");
            return;
        }

        // Check admin
        AdminDTO admin = adminDAO.getAdminByUsername(username);
        if (admin != null && admin.getPassword().equals(password)) {
            session.setAttribute("username", admin.getUsername());
            session.setAttribute("user", admin.getFirstname());
            session.setAttribute("role", "admin");
            response.sendRedirect("admin_dashboard.jsp");
            return;
        }

        // Failed login
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<script>alert('Invalid username or password');location='login.jsp';</script>");
    }
}
