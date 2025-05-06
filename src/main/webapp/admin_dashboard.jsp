<%@ page session="true" import="java.util.*, java.io.*" %>
<%
    if (!"admin".equals(session.getAttribute("role"))) {
        response.sendRedirect("index.jsp");
        return;
    }
%>
<html>
<head><title>Admin Panel</title></head>
<body>
<h2>Welcome Admin: <%= session.getAttribute("user") %></h2>
<a href="signout">Sign Out</a>
<h3>Registered Customers</h3>
<table border="1">
    <tr><th>Username</th><th>Password</th><th>First Name</th></tr>
    <%
        String path = application.getRealPath("/") + "users.txt";
        BufferedReader reader = new BufferedReader(new FileReader(path));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",");
            if (parts.length == 3) {
    %>
    <tr>
        <td><%= parts[0] %></td>
        <td><%= parts[1] %></td>
        <td><%= parts[2] %></td>
    </tr>
    <%
            }
        }
        reader.close();
    %>
</table>
</body>
</html>
