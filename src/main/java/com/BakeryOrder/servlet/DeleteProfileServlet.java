package com.BakeryOrder.servlet;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class DeleteProfileServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");

        String path = getServletContext().getRealPath("/") + "users.txt";
        File inputFile = new File(path);
        File tempFile = new File(path + ".tmp");

        BufferedReader reader = new BufferedReader(new FileReader(inputFile));
        BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",");
            if (!parts[0].equals(username)) {
                writer.write(line);
                writer.newLine();
            }
        }

        writer.close();
        reader.close();

        inputFile.delete();
        tempFile.renameTo(inputFile);

        session.invalidate();
        response.sendRedirect("index.jsp");
    }
}
