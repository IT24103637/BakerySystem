<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    String user = (String) session.getAttribute("user");
    String role = (String) session.getAttribute("role");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Bakery Dashboard</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background-color: #f8f9fa;
        }
        .header {
            background-color: #343a40;
            color: white;
            padding: 1rem 2rem;
            display: flex;
            justify-content: space-between;
            align-items: center;
        }
        .header a {
            color: #f8f9fa;
            margin-left: 15px;
            text-decoration: none;
        }
        .footer {
            background-color: #343a40;
            color: white;
            text-align: center;
            padding: 1rem;
            position: fixed;
            bottom: 0;
            width: 100%;
        }
        .dashboard-content {
            margin-top: 3rem;
        }
        .card {
            border-radius: 12px;
            box-shadow: 0 2px 8px rgba(0,0,0,0.1);
        }
    </style>
</head>
<body>

<!-- Header -->
<div class="header">
    <h2>Bakery System</h2>
    <div>
        <% if (user != null) { %>
        <span>Welcome, <%= user %></span>
        <% if ("admin".equals(role)) { %>
        <a href="admin_dashboard.jsp">Admin Panel</a>
        <% } else { %>
        <a href="profile.jsp">Profile</a>
        <% } %>
        <a href="signout">Sign Out</a>
        <% } else { %>
        <a href="login.jsp">Login</a>
        <a href="signup.jsp">Signup</a>
        <a href="admin_signup.jsp">Admin Signup</a>
        <% } %>
    </div>
</div>

<!-- Dashboard Content -->
<div class="container dashboard-content">
    <div class="text-center">
        <h1>Welcome to Our Bakery!</h1>
        <p>Delicious Cakes, Breads, and More.</p>
        <img src="https://via.placeholder.com/600x250?text=Bakery+Dashboard" class="img-fluid rounded shadow" alt="Bakery Banner">
    </div>

    <div class="row mt-4">
        <div class="col-md-4">
            <div class="card p-3 text-center">
                <h4>Fresh Cakes</h4>
                <p>Order your favorite cakes for every occasion.</p>
            </div>
        </div>
        <div class="col-md-4">
            <div class="card p-3 text-center">
                <h4>Special Breads</h4>
                <p>Try our freshly baked breads and pastries.</p>
            </div>
        </div>
        <div class="col-md-4">
            <div class="card p-3 text-center">
                <h4>Custom Orders</h4>
                <p>Create custom cakes with your designs and flavors.</p>
            </div>
        </div>
    </div>
</div>

<!-- Footer -->
<div class="footer">
    &copy; 2025 Bakery System. All Rights Reserved.
</div>

</body>
</html>
