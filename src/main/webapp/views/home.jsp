<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Sweet Delights Bakery - Dashboard</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/styles.css">
    <style>
        .dashboard-stats {
            display: flex;
            flex-wrap: wrap;
            gap: 15px;
            margin-bottom: 20px;
        }
        .stat-card {
            background-color: #f8f1e9;
            border-radius: 8px;
            padding: 15px;
            flex: 1;
            min-width: 200px;
            box-shadow: 0 2px 4px rgba(0,0,0,0.1);
        }
        .stat-card h3 {
            margin-top: 0;
            color: #5a4a42;
        }
        .stat-value {
            font-size: 1.8em;
            font-weight: bold;
            color: #d9a7a7;
        }
        .processing-order {
            background-color: #e8f4f8;
            padding: 15px;
            border-radius: 8px;
            margin-bottom: 20px;
        }
        .todays-orders table {
            width: 100%;
        }
    </style>
</head>
<body>
<div class="container">
    <div class="header">
        <h1>Sweet Delights Bakery</h1>
        <p>Order Management Dashboard</p>
    </div>

    <div class="nav-menu">
        <a href="${pageContext.request.contextPath}/home" class="active">Dashboard</a>
        <a href="${pageContext.request.contextPath}/orders/list">All Orders</a>
        <a href="${pageContext.request.contextPath}/orders/create">New Order</a>
    </div>

    <div class="dashboard-stats">
        <div class="stat-card">
            <h3>Total Orders</h3>
            <p class="stat-value">${totalOrders}</p>
        </div>
        <div class="stat-card">
            <h3>Pending Orders</h3>
            <p class="stat-value">${pendingOrders}</p>
        </div>
        <div class="stat-card">
            <h3>Completed Today</h3>
            <p class="stat-value">${completedToday}</p>
        </div>
    </div>

    <c:if test="${not empty processingOrder}">
        <div class="processing-order">
            <h2>Currently Processing</h2>
            <p><strong>Order #:</strong> ${processingOrder.id}</p>
            <p><strong>Customer:</strong> ${processingOrder.customerName}</p>
            <p><strong>Pickup Time:</strong>
                <fmt:formatDate value="${processingOrder.pickupTime}" pattern="MMM dd, yyyy hh:mm a"/>
            </p>
            <a href="${pageContext.request.contextPath}/orders/complete?id=${processingOrder.id}"
               class="btn btn-primary">Mark as Completed</a>
        </div>
    </c:if>

    <div class="card todays-orders">
        <h2>Today's Orders</h2>
        <c:choose>
            <c:when test="${empty todaysOrders}">
                <p>No orders placed today.</p>
            </c:when>
            <c:otherwise>
                <table class="table">
                    <thead>
                    <tr>
                        <th>Order ID</th>
                        <th>Customer</th>
                        <th>Items</th>
                        <th>Total</th>
                        <th>Status</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${todaysOrders}" var="order">
                        <tr>
                            <td><a href="${pageContext.request.contextPath}/orders/details?id=${order.id}">#${order.id.substring(0, 8)}</a></td>
                            <td>${order.customerName}</td>
                            <td>${order.itemCount} items</td>
                            <td><fmt:formatNumber value="${order.totalAmount}" type="currency"/></td>
                            <td>
                                        <span class="status-badge status-${order.status.toString().toLowerCase()}">
                                                ${order.status.toString()}
                                        </span>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </c:otherwise>
        </c:choose>
    </div>

    <div class="footer">
        <p>&copy; 2025 Sweet Delights Bakery Order System</p>
    </div>
</div>
</body>
</html>