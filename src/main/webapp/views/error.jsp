<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isErrorPage="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Error - Sweet Delights Bakery</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/styles.css">
    <style>
        .error-container {
            max-width: 800px;
            margin: 0 auto;
            padding: 20px;
            background-color: #fee;
            border-radius: 8px;
            border: 1px solid #f99;
        }
        .error-details {
            background-color: #fff;
            padding: 15px;
            border-radius: 5px;
            margin-top: 20px;
            font-family: monospace;
            overflow-x: auto;
        }
        .error-actions {
            margin-top: 20px;
        }
    </style>
</head>
<body>
<div class="container">
    <div class="header">
        <h1>Sweet Delights Bakery</h1>
        <p>Error Occurred</p>
    </div>

    <div class="nav-menu">
        <a href="${pageContext.request.contextPath}/home">Home</a>
        <a href="${pageContext.request.contextPath}/orders/list">All Orders</a>
    </div>

    <div class="error-container">
        <h2>Oops! Something went wrong</h2>

        <c:choose>
            <c:when test="${not empty requestScope['javax.servlet.error.message']}">
                <p><strong>Error:</strong> ${requestScope['javax.servlet.error.message']}</p>
            </c:when>
            <c:when test="${not empty exception}">
                <p><strong>Error:</strong> ${exception.message}</p>
            </c:when>
            <c:otherwise>
                <p>An unexpected error occurred while processing your request.</p>
            </c:otherwise>
        </c:choose>

        <c:if test="${not empty statusCode}">
            <p><strong>Status Code:</strong> ${statusCode}</p>
        </c:if>

        <div class="error-actions">
            <a href="${pageContext.request.contextPath}/home" class="btn">Return to Home</a>
            <a href="javascript:history.back()" class="btn">Go Back</a>
        </div>

        <c:if test="${exception != null}">
            <div class="error-details">
                <h4>Technical Details:</h4>
                <pre>${exception}</pre>
                <c:forEach items="${exception.stackTrace}" var="trace">
                    ${trace}<br>
                </c:forEach>
            </div>
        </c:if>
    </div>

    <div class="footer">
        <p>&copy; 2025 Sweet Delights Bakery Order System</p>
    </div>
</div>
</body>
</html>