package com.BakeryOrder.servlet;

import com.BakeryOrder.dao.AdminDAO;
import com.BakeryOrder.dto.AdminDTO;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class AdminSignupServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String firstname = request.getParameter("firstname");

        String adminFile = getServletContext().getRealPath("/") + "admins.txt";
        AdminDAO adminDAO = new AdminDAO(adminFile);

        if (adminDAO.getAdminByUsername(username) != null) {
            response.getWriter().println("<script>alert('Admin already exists');location='admin_signup.jsp';</script>");
            return;
        }

        AdminDTO newAdmin = new AdminDTO(username, password, firstname);
        adminDAO.saveAdmin(newAdmin);
        response.sendRedirect("login.jsp");
    }
}
