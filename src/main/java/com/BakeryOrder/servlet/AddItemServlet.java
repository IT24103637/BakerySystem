package com.BakeryOrder.servlet;

import com.BakeryOrder.dao.ProductDAO;
import com.BakeryOrder.dto.ProductDTO;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;
import java.io.*;

@MultipartConfig
public class AddItemServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        String category = request.getParameter("category");
        String number = request.getParameter("number");
        String name = request.getParameter("name");
        String price = request.getParameter("price");

        Part imagePart = request.getPart("image");
        String imageName = imagePart.getSubmittedFileName();
        String uploadPath = getServletContext().getRealPath("/") + "images/";
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) uploadDir.mkdir();

        imagePart.write(uploadPath + imageName);

        String productFile = getServletContext().getRealPath("/") + "products.txt";
        ProductDAO dao = new ProductDAO(productFile);

        ProductDTO newProduct = new ProductDTO(category, number, name, price, imageName);
        dao.addProduct(newProduct);

        response.sendRedirect("ViewCategoryServlet?category=" + category);
    }
}
