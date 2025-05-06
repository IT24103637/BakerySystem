<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="true" %>
<%
  String username = (String) session.getAttribute("username");
  String firstname = (String) session.getAttribute("user");

  if (username == null) {
    response.sendRedirect("login.jsp");
    return;
  }
%>
<!DOCTYPE html>
<html>
<head>
  <title>Edit Profile | Bakery Delight</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">

<nav class="navbar navbar-expand-lg navbar-light bg-white shadow-sm">
  <div class="container-fluid">
    <a class="navbar-brand" href="#">🍰 Bakery Delight</a>
    <div class="d-flex">
      <span class="navbar-text me-3">Welcome, <%= firstname %></span>
      <a href="SignoutServlet" class="btn btn-outline-danger">Sign Out</a>
    </div>
  </div>
</nav>

<div class="container mt-5">
  <h2 class="text-center mb-4">Edit Profile</h2>
  <form action="updateProfileServlet" method="post">
    <div class="mb-3">
      <label class="form-label">Username</label>
      <input type="text" name="username" class="form-control" value="<%= username %>" readonly />
    </div>
    <div class="mb-3">
      <label class="form-label">First Name</label>
      <input type="text" name="firstname" class="form-control" value="<%= firstname %>" required />
    </div>
    <div class="mb-3">
      <label class="form-label">New Password (Leave blank to keep existing)</label>
      <input type="password" name="password" class="form-control" />
    </div>
    <button type="submit" class="btn btn-success w-100">Save Changes</button>
  </form>
</div>

</body>
</html>
