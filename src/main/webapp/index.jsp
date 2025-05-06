<%
    String user = (String) session.getAttribute("user");
    String role = (String) session.getAttribute("role");
    if (user != null) {
%>
<span>Welcome <%= user %></span>
<% if ("admin".equals(role)) { %>
| <a href="admin_dashboard.jsp">Admin Panel</a>
<% } else { %>
| <a href="profile.jsp">Profile</a>
<% } %>
| <a href="signout">Sign Out</a>
<% } else { %>
<a href="login.jsp">Login</a> |
<a href="signup.jsp">Signup</a> |
<a href="admin_signup.jsp">Admin Signup</a>
<% } %>
