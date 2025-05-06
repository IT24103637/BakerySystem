package com.BakeryOrder.servlet;

import javax.servlet.http.*;
import java.io.*;

public class SignOutServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        request.getSession().invalidate();
        response.sendRedirect("index.jsp");
    }
}
