package com.BakeryOrder.servlet;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class SignupServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String firstName = request.getParameter("firstname");

        String path = getServletContext().getRealPath("/") + "users.txt";
        BufferedWriter writer = new BufferedWriter(new FileWriter(path, true));
        writer.write(username + "," + password + "," + firstName);
        writer.newLine();
        writer.close();

        HttpSession session = request.getSession();
        session.setAttribute("username", username);
        session.setAttribute("password", password);
        session.setAttribute("user", firstName);
        session.setAttribute("user", firstName);

        response.sendRedirect("index.jsp");
    }
}
