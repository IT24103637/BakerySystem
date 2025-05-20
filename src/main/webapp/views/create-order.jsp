<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Bakery Order System - Place New Order</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/styles.css">
    <script>
        function addItem() {
            const itemsContainer = document.getElementById('order-items');
            const itemCount = document.querySelectorAll('.order-item').length;

            const newItem = document.createElement('div');
            newItem.className = 'order-item card';
            newItem.innerHTML = `
                <div class="form-group">
                    <label>Item:</label>
                    <select name="items[${itemCount}].productId" required>
                        <option value="">Select an item</option>
                        <option value="1">Chocolate Cake (8") - $25.00</option>
                        <option value="2">Vanilla Cupcake (Dozen) - $15.00</option>
                        <option value="3">Red Velvet Cake (6") - $20.00</option>
                        <option value="4">Bread Loaf - $8.00</option>
                        <option value="5">Cookies (12 pcs) - $12.00</option>
                    </select>
                </div>
                <div class="form-group">
                    <label>Quantity:</label>
                    <input type="number" name="items[${itemCount}].quantity" min="1" value="1" required>
                </div>
                <button type="button" class="btn btn-danger" onclick="this.parentNode.remove()">Remove Item</button>
            `;

            itemsContainer.appendChild(newItem);
        }
    </script>
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

    <h2>Place New Order</h2>

    <c:if test="${not empty error}">
        <div class="card" style="background-color: #fee;">
                ${error}
        </div>
    </c:if>

    <form action="${pageContext.request.contextPath}/orders/create" method="post" class="card">
        <div class="form-group">
            <label for="customerName">Your Name:</label>
            <input type="text" id="customerName" name="customerName" required>
        </div>

        <div class="form-group">
            <label for="contactNumber">Contact Number:</label>
            <input type="tel" id="contactNumber" name="contactNumber" required>
        </div>

        <div class="form-group">
            <label for="pickupTime">Pickup Date & Time:</label>
            <input type="datetime-local" id="pickupTime" name="pickupTime" required>
        </div>

        <h3>Order Items</h3>
        <div id="order-items">
            <!-- Initial item -->
            <div class="order-item card">
                <div class="form-group">
                    <label>Item:</label>
                    <select name="items[0].productId" required>
                        <option value="">Select an item</option>
                        <option value="1">Chocolate Cake (8") - $25.00</option>
                        <option value="2">Vanilla Cupcake (Dozen) - $15.00</option>
                        <option value="3">Red Velvet Cake (6") - $20.00</option>
                        <option value="4">Bread Loaf - $8.00</option>
                        <option value="5">Cookies (12 pcs) - $12.00</option>
                    </select>
                </div>
                <div class="form-group">
                    <label>Quantity:</label>
                    <input type="number" name="items[0].quantity" min="1" value="1" required>
                </div>
            </div>
        </div>

        <button type="button" class="btn btn-primary" onclick="addItem()">Add Another Item</button>

        <div class="form-group">
            <label for="specialInstructions">Special Instructions:</label>
            <textarea id="specialInstructions" name="specialInstructions" rows="3"></textarea>
        </div>

        <div style="margin-top: 20px;">
            <button type="submit" class="btn">Place Order</button>
            <a href="${pageContext.request.contextPath}/orders" class="btn btn-danger">Cancel</a>
        </div>
    </form>

    <div class="footer">
        &copy; 2025 Sweet Delights Bakery Order System
    </div>
</div>
</body>
</html>