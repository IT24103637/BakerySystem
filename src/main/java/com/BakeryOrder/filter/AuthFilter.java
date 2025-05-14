package com.BakeryOrder.filter;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class AuthFilter implements Filter {
    public void init(FilterConfig filterConfig) throws ServletException {}

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        HttpSession session = req.getSession(false);
        String role = (session != null) ? (String) session.getAttribute("role") : null;

        String uri = req.getRequestURI();

        if (uri.contains("admin_dashboard.jsp") && !"admin".equals(role)) {
            res.sendRedirect("login.jsp");
            return;
        }

        chain.doFilter(request, response);
    }

    public void destroy() {}
}
