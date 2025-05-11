<%@ page session="true" import="java.util.*, java.io.*, com.BakeryOrder.dto.UserDTO, com.BakeryOrder.dao.UserDAO" %>
<%
    if (!"admin".equals(session.getAttribute("role"))) {
        response.sendRedirect("index.jsp");
        return;
    }

    String path = application.getRealPath("/") + "users.txt";
    UserDAO userDAO = new UserDAO(path);
    List<UserDTO> users = userDAO.getAllUsers();
%>
<html>
<head>
    <title>Admin Panel</title>
    <style>
        table { border-collapse: collapse; width: 100%; }
        th, td { border: 1px solid #ddd; padding: 8px; text-align: left; }
        th { background-color: #f2f2f2; }
        tr:nth-child(even) { background-color: #f9f9f9; }
    </style>
</head>
<body>
<h2>Welcome Admin: <%= session.getAttribute("user") %></h2>
<a href="signout">Sign Out</a>

<h3>Registered Customers</h3>
<table>
    <tr>
        <th>Username</th>
        <th>Password</th>
        <th>First Name</th>
        <th>Action</th>
    </tr>
    <% for (UserDTO user : users) { %>
    <tr>
        <td><%= user.getUsername() %></td>
        <td><%= user.getPassword() %></td>
        <td><%= user.getFirstName() %></td>
        <td>
            <form action="DeleteCustomerServlet" method="post" style="display:inline;">
                <input type="hidden" name="username" value="<%= user.getUsername() %>">
                <input type="submit" value="Delete" onclick="return confirm('Are you sure you want to delete this user?');">
            </form>
        </td>
    </tr>
    <% } %>
</table>
</body>
</html>