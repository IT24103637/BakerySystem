<%@ page session="true" import="java.util.*, com.BakeryOrder.dto.UserDTO, com.BakeryOrder.dao.UserDAO" %>
<%
    if (!"admin".equals(session.getAttribute("role"))) {
        response.sendRedirect("index.jsp");
        return;
    }

    String path = application.getRealPath("/") + "users.txt";
    UserDAO userDAO = new UserDAO(path);
    List<UserDTO> users = userDAO.getAllUsers();
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Admin Panel</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">

<div class="container mt-5">
    <div class="card shadow p-4">
        <h2>Welcome Admin: <%= session.getAttribute("user") %></h2>
        <a href="signout" class="btn btn-danger float-end">Sign Out</a>
        <hr>

        <h3>Registered Customers</h3>

        <table class="table table-bordered table-striped mt-3">
            <thead class="table-dark">
            <tr>
                <th>Username</th>
                <th>Password</th>
                <th>First Name</th>
                <th>Action</th>
            </tr>
            </thead>
            <tbody>
            <% for (UserDTO user : users) { %>
            <tr>
                <td><%= user.getUsername() %></td>
                <td><%= user.getPassword() %></td>
                <td><%= user.getFirstName() %></td>
                <td>
                    <form action="DeleteCustomerServlet" method="post" class="d-inline">
                        <input type="hidden" name="username" value="<%= user.getUsername() %>">
                        <button type="submit" class="btn btn-danger btn-sm" onclick="return confirm('Are you sure you want to delete this user?');">Delete</button>
                    </form>
                </td>
            </tr>
            <% } %>
            </tbody>
        </table>

        <a href="index.jsp" class="btn btn-secondary mt-3">Back to Dashboard</a>
    </div>
</div>

</body>
</html>
