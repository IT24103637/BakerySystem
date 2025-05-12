<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Admin Signup</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">

<div class="container mt-5">
    <div class="card shadow p-4">
        <h2 class="mb-4">Admin Signup</h2>

        <form action="AdminSignup" method="post">
            <div class="mb-3">
                <label class="form-label">First Name:</label>
                <input type="text" name="firstname" required class="form-control">
            </div>
            <div class="mb-3">
                <label class="form-label">Username:</label>
                <input type="text" name="username" required class="form-control">
            </div>
            <div class="mb-3">
                <label class="form-label">Password:</label>
                <input type="password" name="password" required class="form-control">
            </div>
            <button type="submit" class="btn btn-success">Register Admin</button>
        </form>

        <div class="mt-3">
            <a href="login.jsp">Back to Login</a>
        </div>
    </div>
</div>

</body>
</html>
