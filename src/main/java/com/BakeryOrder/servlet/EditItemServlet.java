package com.BakeryOrder.servlet;

import com.BakeryOrder.dao.ProductDAO;
import com.BakeryOrder.dto.ProductDTO;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.*;
import java.util.List;

public class EditItemServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        String category = request.getParameter("category");
        String number = request.getParameter("number");
        String name = request.getParameter("name");
        String price = request.getParameter("price");
        String image = request.getParameter("image");

        String productFile = getServletContext().getRealPath("/") + "products.txt";
        ProductDAO dao = new ProductDAO(productFile);
        List<ProductDTO> products = dao.getProductsByCategory(category);

        for (ProductDTO p : products) {
            if (p.getNumber().equals(number)) {
                p.setName(name);
                p.setPrice(price);
                p.setImage(image);
            }
        }

        dao.updateAllProducts(products);
        response.sendRedirect("ViewCategoryServlet?category=" + category);
    }
}
