package com.BakeryOrder.servlet;

import com.BakeryOrder.dao.AdminDAO;
import com.BakeryOrder.dto.AdminDTO;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

public class AdminSignupServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String firstname = request.getParameter("firstname");

        if (username == null || password == null || firstname == null ||
                username.isEmpty() || password.isEmpty() || firstname.isEmpty()) {
            response.sendRedirect("admin_signup.jsp");
            return;
        }

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
