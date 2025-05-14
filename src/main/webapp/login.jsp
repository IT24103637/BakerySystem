<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">

<div class="container mt-5">
    <div class="card shadow p-4">
        <h2>Login</h2>

        <form action="login" method="post">
            <div class="mb-3">
                <label class="form-label">Username:</label>
                <input type="text" name="username" required class="form-control">
            </div>
            <div class="mb-3">
                <label class="form-label">Password:</label>
                <input type="password" name="password" required class="form-control">
            </div>
            <button type="submit" class="btn btn-primary">Login</button>
        </form>

        <p class="text-danger mt-3">
            <%= request.getAttribute("error") != null ? request.getAttribute("error") : "" %>
        </p>

        <div class="mt-3">
            <a href="signup.jsp">Customer Signup</a> |
        </div>
    </div>
</div>

</body>
</html>
