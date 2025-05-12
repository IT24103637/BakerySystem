<%@ page session="true" %>
<%
    String username = (String) session.getAttribute("username");
    String password = (String) session.getAttribute("password");
    String firstName = (String) session.getAttribute("user");

    if (username == null) {
        response.sendRedirect("login.jsp");
    }
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Your Profile</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">

<div class="container mt-5">
    <div class="card shadow p-4">
        <h2 class="mb-4">Your Profile</h2>

        <p><strong>Username:</strong> <%= username %></p>

        <form action="edit" method="post">
            <div class="mb-3">
                <label class="form-label">New Password:</label>
                <input type="password" name="password" value="<%= password %>" required class="form-control">
            </div>
            <div class="mb-3">
                <label class="form-label">First Name:</label>
                <input type="text" name="firstname" value="<%= firstName %>" required class="form-control">
            </div>
            <button type="submit" class="btn btn-primary">Update Profile</button>
        </form>

        <form action="delete" method="post" class="mt-3" onsubmit="return confirm('Are you sure you want to delete your profile?');">
            <button type="submit" class="btn btn-danger">Delete Profile</button>
        </form>

        <div class="mt-4">
            <a href="index.jsp" class="btn btn-outline-secondary">Back to Dashboard</a>
            <a href="signout" class="btn btn-outline-danger">Sign Out</a>
        </div>
    </div>
</div>

</body>
</html>
