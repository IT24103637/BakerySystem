<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Sweet Bakery -Inventory </title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/inventory.css">
</head>
<body>
<div class="inventory-container">
    <header class="inventory-header">
        <h1><img src="${pageContext.request.contextPath}/images/pastry-icon.png" alt="Pastry Icon" class="header-icon"> Welcome to Sweet Bakery-Inventory</h1>
        <a href="add-item" class="btn-add">Add New Item</a>
    </header>

    <div class="alert-container">
        <c:if test="${not empty message}">
            <div class="alert ${messageType}">${message}</div>
        </c:if>
    </div>

    <div class="search-filter">
        <input type="text" id="searchInput" placeholder="Search inventory..." class="search-input">
        <select id="categoryFilter" class="filter-select">
            <option value="">All Categories</option>
            <option value="Ingredient">Ingredients</option>
            <option value="Equipment">Equipment</option>
            <option value="Packaging">Packaging</option>
        </select>
    </div>

    <table class="inventory-table">
        <thead>
        <tr>
            <th>ID</th>
            <th>Item Name</th>
            <th>Category</th>
            <th>Quantity</th>
            <th>Unit</th>
            <th>Threshold</th>
            <th>Status</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${inventoryItems}" var="item">
            <tr class="${item.quantity < item.threshold ? 'low-stock' : ''}">
                <td>${item.id}</td>
                <td>${item.name}</td>
                <td>${item.category}</td>
                <td>${item.quantity}</td>
                <td>${item.unit}</td>
                <td>${item.threshold}</td>
                <td>
                            <span class="status-badge ${item.quantity < item.threshold ? 'badge-warning' : 'badge-success'}">
                                    ${item.quantity < item.threshold ? 'Low Stock' : 'In Stock'}
                            </span>
                </td>
                <td>
                    <a href="update-item?id=${item.id}" class="btn-action btn-edit">Edit</a>
                    <a href="delete-item?id=${item.id}" class="btn-action btn-delete" onclick="return confirm('Are you sure you want to delete this item?')">Delete</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <div class="inventory-summary">
        <div class="summary-card">
            <h3>Total Items</h3>
            <p>${totalItems}</p>
        </div>
        <div class="summary-card warning">
            <h3>Low Stock Items</h3>
            <p>${lowStockItems}</p>
        </div>
        <div class="summary-card critical">
            <h3>Out of Stock</h3>
            <p>${outOfStockItems}</p>
        </div>
    </div>
</div>

<script src="${pageContext.request.contextPath}/js/inventory.js"></script>
</body>
</html>