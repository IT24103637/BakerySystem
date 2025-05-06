package com.BakeryOrder.servlet;

import javax.servlet.http.*;
import java.io.*;

public class AdminSignupServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String firstname = request.getParameter("firstname");

        String path = getServletContext().getRealPath("/") + "admins.txt";
        BufferedWriter writer = new BufferedWriter(new FileWriter(path, true));
        writer.write(username + "," + password + "," + firstname);
        writer.newLine();
        writer.close();

        HttpSession session = request.getSession();
        session.setAttribute("username", username);
        session.setAttribute("user", firstname);
        session.setAttribute("role", "admin");

        response.sendRedirect("admin_dashboard.jsp");
    }
}
