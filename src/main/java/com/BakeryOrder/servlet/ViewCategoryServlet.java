package com.BakeryOrder.servlet;

import com.BakeryOrder.dao.ProductDAO;
import com.BakeryOrder.dto.ProductDTO;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class ViewCategoryServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String category = request.getParameter("category");
        if (category == null || category.isEmpty()) {
            response.sendRedirect("index.jsp");
            return;
        }

        String productFile = getServletContext().getRealPath("/") + "products.txt";
        ProductDAO productDAO = new ProductDAO(productFile);
        List<ProductDTO> allProducts = productDAO.getAllProducts();

        List<ProductDTO> filtered = allProducts.stream()
                .filter(p -> category.equalsIgnoreCase(p.getCategory()))
                .collect(Collectors.toList());

        request.setAttribute("category", category);
        request.setAttribute("products", filtered);

        // Forward before any response is committed
        RequestDispatcher rd = request.getRequestDispatcher("category_items.jsp");
        rd.forward(request, response);
    }
}
