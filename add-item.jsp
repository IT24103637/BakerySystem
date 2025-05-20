<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Add New Inventory Item</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/inventory.css">
</head>
<body>
<div class="form-container">
  <header class="form-header">
    <h1>Add New Inventory Item</h1>
    <a href="inventory" class="btn-back">Back to Inventory</a>
  </header>

  <div class="alert-container">
    <c:if test="${not empty message}">
      <div class="alert ${messageType}">${message}</div>
    </c:if>
  </div>

  <form action="add-item" method="post" class="inventory-form">
    <div class="form-group">
      <label for="name">Item Name:</label>
      <input type="text" id="name" name="name" required class="form-input">
    </div>

    <div class="form-group">
      <label for="category">Category:</label>
      <select id="category" name="category" required class="form-select">
        <option value="">Select Category</option>
        <option value="Ingredient">Ingredient</option>
        <option value="Equipment">Equipment</option>
        <option value="Packaging">Packaging</option>
      </select>
    </div>

    <div class="form-row">
      <div class="form-group">
        <label for="quantity">Quantity:</label>
        <input type="number" id="quantity" name="quantity" min="0" step="0.01" required class="form-input">
      </div>

      <div class="form-group">
        <label for="unit">Unit:</label>
        <select id="unit" name="unit" required class="form-select">
          <option value="">Select Unit</option>
          <option value="kg">Kilograms (kg)</option>
          <option value="g">Grams (g)</option>
          <option value="L">Liters (L)</option>
          <option value="ml">Milliliters (ml)</option>
          <option value="units">Units</option>
          <option value="packets">Packets</option>
        </select>
      </div>
    </div>

    <div class="form-group">
      <label for="threshold">Low Stock Threshold:</label>
      <input type="number" id="threshold" name="threshold" min="0" step="0.01" required class="form-input">
      <small class="form-help">System will alert when stock falls below this level</small>
    </div>

    <div class="form-group">
      <label for="supplier">Supplier Information:</label>
      <textarea id="supplier" name="supplier" rows="3" class="form-textarea"></textarea>
    </div>

    <div class="form-actions">
      <button type="reset" class="btn-reset">Reset</button>
      <button type="submit" class="btn-submit">Add Item</button>
    </div>
  </form>
</div>
</body>
</html>
