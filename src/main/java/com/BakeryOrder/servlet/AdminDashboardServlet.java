package com.BakeryOrder.servlet;

import com.BakeryOrder.dao.UserDAO;
import com.BakeryOrder.dto.UserDTO;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;

public class AdminDashboardServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (session == null || !"admin".equals(session.getAttribute("role"))) {
            response.sendRedirect("login.jsp");
            return;
        }

        String path = getServletContext().getRealPath("/") + "users.txt";
        UserDAO userDAO = new UserDAO(path);
        List<UserDTO> customers = userDAO.getAllUsers();

        request.setAttribute("customers", customers);
        RequestDispatcher dispatcher = request.getRequestDispatcher("admin_dashboard.jsp");
        dispatcher.forward(request, response);
    }
}
