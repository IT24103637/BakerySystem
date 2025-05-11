package com.BakeryOrder.servlet;

import com.BakeryOrder.dao.UserDAO;
import com.BakeryOrder.dto.UserDTO;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class SignupServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String firstname = request.getParameter("firstname");

        String userFile = getServletContext().getRealPath("/") + "users.txt";
        UserDAO userDAO = new UserDAO(userFile);

        if (userDAO.getUserByUsername(username) != null) {
            response.getWriter().println("<script>alert('Username already exists');location='signup.jsp';</script>");
            return;
        }

        UserDTO newUser = new UserDTO(username, password, firstname);
        userDAO.saveUser(newUser);
        response.sendRedirect("login.jsp");
    }
}
