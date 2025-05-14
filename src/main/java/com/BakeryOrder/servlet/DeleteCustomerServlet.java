package com.BakeryOrder.servlet;

import com.BakeryOrder.dao.UserDAO;
import com.BakeryOrder.dto.UserDTO;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DeleteCustomerServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        String usernameToDelete = request.getParameter("username");

        if (usernameToDelete == null || usernameToDelete.isEmpty()) {
            response.sendRedirect("AdminPanel");
            return;
        }

        String userFile = getServletContext().getRealPath("/") + "users.txt";
        UserDAO userDAO = new UserDAO(userFile);

        List<UserDTO> allUsers = userDAO.getAllUsers();
        List<UserDTO> updatedUsers = new ArrayList<>();

        for (UserDTO user : allUsers) {
            if (!user.getUsername().equals(usernameToDelete)) {
                updatedUsers.add(user);
            }
        }

        userDAO.updateUsers(updatedUsers);

        response.sendRedirect("AdminPanel");
    }
}
