<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Update Inventory Item</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/inventory.css">
</head>
<body>
<div class="form-container">
    <header class="form-header">
        <h1>Update Inventory Item</h1>
        <a href="inventory" class="btn-back">Back to Inventory</a>
    </header>

    <div class="alert-container">
        <c:if test="${not empty message}">
            <div class="alert ${messageType}">${message}</div>
        </c:if>
    </div>

    <form action="update-item" method="post" class="inventory-form">
        <input type="hidden" name="id" value="${item.id}">

        <div class="form-group">
            <label for="name">Item Name:</label>
            <input type="text" id="name" name="name" value="${item.name}" required class="form-input">
        </div>

        <div class="form-group">
            <label for="category">Category:</label>
            <select id="category" name="category" required class="form-select">
                <option value="Ingredient" ${item.category == 'Ingredient' ? 'selected' : ''}>Ingredient</option>
                <option value="Equipment" ${item.category == 'Equipment' ? 'selected' : ''}>Equipment</option>
                <option value="Packaging" ${item.category == 'Packaging' ? 'selected' : ''}>Packaging</option>
            </select>
        </div>

        <div class="form-row">
            <div class="form-group">
                <label for="quantity">Quantity:</label>
                <input type="number" id="quantity" name="quantity" min="0" step="0.01" value="${item.quantity}" required class="form-input">
            </div>

            <div class="form-group">
                <label for="unit">Unit:</label>
                <select id="unit" name="unit" required class="form-select">
                    <option value="kg" ${item.unit == 'kg' ? 'selected' : ''}>Kilograms (kg)</option>
                    <option value="g" ${item.unit == 'g' ? 'selected' : ''}>Grams (g)</option>
                    <option value="L" ${item.unit == 'L' ? 'selected' : ''}>Liters (L)</option>
                    <option value="ml" ${item.unit == 'ml' ? 'selected' : ''}>Milliliters (ml)</option>
                    <option value="units" ${item.unit == 'units' ? 'selected' : ''}>Units</option>
                    <option value="packets" ${item.unit == 'packets' ? 'selected' : ''}>Packets</option>
                </select>
            </div>
        </div>

        <div class="form-group">
            <label for="threshold">Low Stock Threshold:</label>
            <input type="number" id="threshold" name="threshold" min="0" step="0.01" value="${item.threshold}" required class="form-input">
            <small class="form-help">System will alert when stock falls below this level</small>
        </div>

        <div class="form-group">
            <label for="supplier">Supplier Information:</label>
            <textarea id="supplier" name="supplier" rows="3" class="form-textarea">${item.supplier}</textarea>
        </div>

        <div class="form-actions">
            <button type="reset" class="btn-reset">Reset</button>
            <button type="submit" class="btn-submit">Update Item</button>
        </div>
    </form>
</div>
</body>
</html>
