<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Bakery Order System - Order Details</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/styles.css">
</head>
<body>
<div class="container">
    <div class="header">
        <h1>Sweet Delights Bakery</h1>
    </div>

    <div class="nav-menu">
        <a href="${pageContext.request.contextPath}/">Home</a>
        <a href="${pageContext.request.contextPath}/orders">My Orders</a>
        <a href="${pageContext.request.contextPath}/orders/create">Place New Order</a>
    </div>

    <h2>Order Details #${order.id}</h2>

    <div class="card">
        <div class="form-group">
            <label>Order Date:</label>
            <p>${order.orderDate}</p>
        </div>

        <div class="form-group">
            <label>Customer Name:</label>
            <p>${order.customerName}</p>
        </div>

        <div class="form-group">
            <label>Contact Number:</label>
            <p>${order.contactNumber}</p>
        </div>

        <div class="form-group">
            <label>Pickup Time:</label>
            <p>${order.pickupTime}</p>
        </div>

        <div class="form-group">
            <label>Status:</label>
            <p class="status-${order.status.toString().toLowerCase()}">${order.status}</p>
        </div>
    </div>

    <div class="card">
        <h3>Order Items</h3>
        <table class="table">
            <thead>
            <tr>
                <th>Item</th>
                <th>Quantity</th>
                <th>Price</th>
                <th>Subtotal</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${order.items}" var="item">
                <tr>
                    <td>${item.name}</td>
                    <td>${item.quantity}</td>
                    <td>$${item.price}</td>
                    <td>$${item.subtotal}</td>
                </tr>
            </c:forEach>
            <tr>
                <td colspan="3" style="text-align: right;"><strong>Total:</strong></td>
                <td><strong>$${order.totalAmount}</strong></td>
            </tr>
            </tbody>
        </table>
    </div>

    <div class="card">
        <div class="form-group">
            <label>Special Instructions:</label>
            <p>${empty order.specialInstructions ? 'None' : order.specialInstructions}</p>
        </div>
    </div>

    <div style="margin-top: 20px;">
        <a href="${pageContext.request.contextPath}/orders" class="btn">Back to Orders</a>
        <c:if test="${order.status.toString() == 'PENDING' || order.status.toString() == 'PROCESSING'}">
            <a href="${pageContext.request.contextPath}/orders/cancel?id=${order.id}" class="btn btn-danger">Cancel Order</a>
        </c:if>
    </div>

    <div class="footer">
        &copy; 2025 Sweet Delights Bakery Order System
    </div>
</div>
</body>
</html>