<%@ page session="true" %>
<html>
<head><title>Profile</title></head>
<body>
<h2>Your Profile</h2>
<%
    String username = (String) session.getAttribute("username");
    String password = (String) session.getAttribute("password");
    String firstName = (String) session.getAttribute("user");

    if (username == null) {
        response.sendRedirect("login.jsp");
    }
%>
<p><b>Username:</b> <%= username %></p>
<form action="edit" method="post">
    New Password: <input type="password" name="password" value="<%= password %>" required><br>
    First Name: <input type="text" name="firstname" value="<%= firstName %>" required><br><br>
    <input type="submit" value="Update Profile">
</form>

<form action="delete" method="post" onsubmit="return confirm('Are you sure you want to delete your profile?');">
    <br><input type="submit" value="Delete Profile" style="color:red;">
</form>
<br><a href="index.jsp">Back to Dashboard</a>
<a href="signout">Sign Out</a>
</body>
</html>
