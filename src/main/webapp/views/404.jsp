<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Page Not Found - Sweet Delights Bakery</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/styles.css">
    <style>
        .not-found-container {
            text-align: center;
            padding: 40px;
        }
        .not-found-icon {
            font-size: 5em;
            color: #d9a7a7;
            margin-bottom: 20px;
        }
        .not-found-actions {
            margin-top: 30px;
        }
    </style>
</head>
<body>
<div class="container">
    <div class="header">
        <h1>Sweet Delights Bakery</h1>
    </div>

    <div class="nav-menu">
        <a href="${pageContext.request.contextPath}/home">Home</a>
        <a href="${pageContext.request.contextPath}/orders/list">All Orders</a>
    </div>

    <div class="not-found-container">
        <div class="not-found-icon">❌</div>
        <h2>404 - Page Not Found</h2>
        <p>The page you are looking for might have been removed, had its name changed, or is temporarily unavailable.</p>

        <div class="not-found-actions">
            <a href="${pageContext.request.contextPath}/home" class="btn btn-primary">Return to Home</a>
            <a href="javascript:history.back()" class="btn">Go Back</a>
        </div>
    </div>

    <div class="footer">
        <p>&copy; 2025 Sweet Delights Bakery Order System</p>
    </div>
</div>
</body>
</html>