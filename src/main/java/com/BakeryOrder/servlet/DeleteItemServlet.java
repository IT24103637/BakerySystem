package com.BakeryOrder.servlet;

import com.BakeryOrder.dao.ProductDAO;
import com.BakeryOrder.dto.ProductDTO;

import javax.servlet.http.*;
import java.io.*;
import java.util.*;

public class DeleteItemServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        String category = request.getParameter("category");
        String number = request.getParameter("number");

        String productFile = getServletContext().getRealPath("/") + "products.txt";
        ProductDAO dao = new ProductDAO(productFile);
        List<ProductDTO> products = dao.getAllProducts();

        products.removeIf(p -> p.getCategory().equals(category) && p.getNumber().equals(number));

        dao.updateAllProducts(products);
        response.sendRedirect("ViewCategoryServlet?category=" + category);
    }
}
