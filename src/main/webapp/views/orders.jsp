<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Sweet Delights Bakery - My Orders</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/styles.css">
    <style>
        .sort-options {
            margin: 15px 0;
            padding: 10px;
            background-color: #f5e6e6;
            border-radius: 5px;
        }
        .sort-options label {
            margin-right: 10px;
            font-weight: bold;
        }
        .sort-options select, .sort-options button {
            padding: 5px 10px;
            margin-right: 10px;
        }
        .queue-status {
            display: flex;
            justify-content: space-between;
            flex-wrap: wrap;
        }
        .queue-stat {
            background-color: #e8f4f8;
            padding: 10px 15px;
            border-radius: 5px;
            margin: 5px;
            flex-grow: 1;
            min-width: 120px;
        }
        .queue-stat h4 {
            margin: 0 0 5px 0;
            color: #2c3e50;
        }
        .queue-stat p {
            margin: 0;
            font-size: 1.2em;
            font-weight: bold;
        }
        .status-badge {
            padding: 3px 8px;
            border-radius: 12px;
            font-size: 0.8em;
            font-weight: bold;
        }
        @media (max-width: 768px) {
            .table {
                display: block;
                overflow-x: auto;
            }
            .queue-status {
                flex-direction: column;
            }
        }
    </style>
</head>
<body>
<div class="container">
    <div class="header">
        <h1>Sweet Delights Bakery</h1>
        <p>Order Management System</p>
    </div>

    <div class="nav-menu">
        <a href="${pageContext.request.contextPath}/">Home</a>
        <a href="${pageContext.request.contextPath}/orders" class="active">My Orders</a>
        <a href="${pageContext.request.contextPath}/orders/create">Place New Order</a>
        <a href="${pageContext.request.contextPath}/orders?sort=pickup">Sort by Pickup Time</a>
    </div>

    <div class="card">
        <h2>Order Queue Status</h2>
        <div class="queue-status">
            <div class="queue-stat">
                <h4>Total Orders</h4>
                <p>${orderService.allOrdersCount}</p>
            </div>
            <div class="queue-stat">
                <h4>Pending in Queue</h4>
                <p>${orderService.pendingOrdersCount}</p>
            </div>
            <div class="queue-stat">
                <h4>Currently Processing</h4>
                <p>
                    <c:choose>
                        <c:when test="${not empty orderService.currentlyProcessingOrder}">
                            #${orderService.currentlyProcessingOrder.id}
                        </c:when>
                        <c:otherwise>None</c:otherwise>
                    </c:choose>
                </p>
            </div>
            <div class="queue-stat">
                <h4>Completed Today</h4>
                <p>${orderService.completedTodayCount}</p>
            </div>
        </div>
    </div>

    <c:if test="${not empty message}">
        <div class="card message-${messageType eq 'error' ? 'error' : 'success'}">
                ${message}
        </div>
    </c:if>

    <div class="sort-options card">
        <form action="${pageContext.request.contextPath}/orders" method="get">
            <label for="sort">Sort Orders:</label>
            <select id="sort" name="sort" onchange="this.form.submit()">
                <option value="">-- Select --</option>
                <option value="date" ${param.sort eq 'date' ? 'selected' : ''}>By Order Date (Newest)</option>
                <option value="date_oldest" ${param.sort eq 'date_oldest' ? 'selected' : ''}>By Order Date (Oldest)</option>
                <option value="pickup" ${param.sort eq 'pickup' ? 'selected' : ''}>By Pickup Time (Earliest)</option>
                <option value="pickup_latest" ${param.sort eq 'pickup_latest' ? 'selected' : ''}>By Pickup Time (Latest)</option>
                <option value="status" ${param.sort eq 'status' ? 'selected' : ''}>By Status</option>
            </select>

            <label for="filter">Filter by Status:</label>
            <select id="filter" name="filter" onchange="this.form.submit()">
                <option value="">All Orders</option>
                <option value="PENDING" ${param.filter eq 'PENDING' ? 'selected' : ''}>Pending</option>
                <option value="PROCESSING" ${param.filter eq 'PROCESSING' ? 'selected' : ''}>Processing</option>
                <option value="COMPLETED" ${param.filter eq 'COMPLETED' ? 'selected' : ''}>Completed</option>
                <option value="CANCELLED" ${param.filter eq 'CANCELLED' ? 'selected' : ''}>Cancelled</option>
            </select>

            <button type="submit" class="btn btn-primary">Apply</button>
            <a href="${pageContext.request.contextPath}/orders" class="btn">Reset</a>
        </form>
    </div>

    <h2>Order History</h2>

    <c:choose>
        <c:when test="${empty orders}">
            <div class="card">
                <p>No orders found.</p>
            </div>
        </c:when>
        <c:otherwise>
            <table class="table">
                <thead>
                <tr>
                    <th>Order ID</th>
                    <th>Date</th>
                    <th>Customer</th>
                    <th>Items</th>
                    <th>Total</th>
                    <th>Pickup Time</th>
                    <th>Status</th>
                    <th>Actions</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${orders}" var="order">
                    <tr>
                        <td>#${order.id.substring(0, 8)}</td>
                        <td><fmt:formatDate value="${order.orderDate}" pattern="MMM dd, yyyy HH:mm"/></td>
                        <td>${order.customerName}</td>
                        <td>${order.itemCount} items</td>
                        <td><fmt:formatNumber value="${order.totalAmount}" type="currency"/></td>
                        <td><fmt:formatDate value="${order.pickupTime}" pattern="MMM dd, yyyy HH:mm"/></td>
                        <td>
                                    <span class="status-badge status-${order.status.toString().toLowerCase()}">
                                            ${order.status.toString()}
                                    </span>
                        </td>
                        <td>
                            <div class="action-buttons">
                                <a href="${pageContext.request.contextPath}/orders/details?id=${order.id}"
                                   class="btn" title="View Details">
                                    View
                                </a>
                                <c:if test="${order.status eq 'PENDING' or order.status eq 'PROCESSING'}">
                                    <a href="${pageContext.request.contextPath}/orders/cancel?id=${order.id}"
                                       class="btn btn-danger" title="Cancel Order"
                                       onclick="return confirm('Are you sure you want to cancel this order?');">
                                        Cancel
                                    </a>
                                </c:if>
                            </div>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>

            <div class="pagination">
                <c:if test="${currentPage > 1}">
                    <a href="${pageContext.request.contextPath}/orders?page=${currentPage - 1}&sort=${param.sort}&filter=${param.filter}"
                       class="btn">Previous</a>
                </c:if>

                <span>Page ${currentPage} of ${totalPages}</span>

                <c:if test="${currentPage < totalPages}">
                    <a href="${pageContext.request.contextPath}/orders?page=${currentPage + 1}&sort=${param.sort}&filter=${param.filter}"
                       class="btn">Next</a>
                </c:if>
            </div>
        </c:otherwise>
    </c:choose>

    <div class="footer">
        <p>&copy; 2025 Sweet Delights Bakery Order System</p>
        <p class="timestamp">Generated on <fmt:formatDate value="${now}" pattern="MMM dd, yyyy HH:mm:ss"/></p>
    </div>
</div>

<script>
    // Highlight current sort option in dropdown
    document.addEventListener('DOMContentLoaded', function() {
        const urlParams = new URLSearchParams(window.location.search);
        const sortParam = urlParams.get('sort');
        const filterParam = urlParams.get('filter');

        if (sortParam) {
            document.getElementById('sort').value = sortParam;
        }

        if (filterParam) {
            document.getElementById('filter').value = filterParam;
        }
    });
</script>
</body>
</html>