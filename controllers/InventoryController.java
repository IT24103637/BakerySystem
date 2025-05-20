package com.BakeryOrder.controllers;

import com.BakeryOrder.model.InventoryItem;
import com.BakeryOrder.services.InventoryService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "InventoryController", urlPatterns = {
        "/inventory",
        "/add-item",
        "/update-item",
        "/delete-item"
})
public class InventoryController extends HttpServlet {
    private InventoryService inventoryService;

    @Override
    public void init() throws ServletException {
        super.init();
        this.inventoryService = new InventoryService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();

        try {
            switch (action) {
                case "/add-item":
                    showAddForm(request, response);
                    break;
                case "/update-item":
                    showUpdateForm(request, response);
                    break;
                case "/delete-item":
                    deleteItem(request, response);
                    break;
                default:
                    listInventory(request, response);
                    break;
            }
        } catch (Exception ex) {
            throw new ServletException(ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();

        try {
            switch (action) {
                case "/add-item":
                    addItem(request, response);
                    break;
                case "/update-item":
                    updateItem(request, response);
                    break;
                default:
                    listInventory(request, response);
                    break;
            }
        } catch (Exception ex) {
            throw new ServletException(ex);
        }
    }

    private void listInventory(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("inventoryItems", inventoryService.getAllInventoryItems());
        request.setAttribute("totalItems", inventoryService.getTotalItemsCount());
        request.setAttribute("lowStockItems", inventoryService.getLowStockItemsCount());
        request.setAttribute("outOfStockItems", inventoryService.getOutOfStockItemsCount());

        request.getRequestDispatcher("/WEB-INF/views/inventory.jsp").forward(request, response);
    }

    private void showAddForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/add-item.jsp").forward(request, response);
    }

    private void showUpdateForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        InventoryItem item = inventoryService.getInventoryItem(id);
        request.setAttribute("item", item);
        request.getRequestDispatcher("/WEB-INF/views/update-item.jsp").forward(request, response);
    }

    private void addItem(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        InventoryItem item = new InventoryItem();
        item.setName(request.getParameter("name"));
        item.setCategory(request.getParameter("category"));
        item.setQuantity(Double.parseDouble(request.getParameter("quantity")));
        item.setUnit(request.getParameter("unit"));
        item.setThreshold(Double.parseDouble(request.getParameter("threshold")));
        item.setSupplier(request.getParameter("supplier"));

        inventoryService.addInventoryItem(item);

        response.sendRedirect("inventory?message=Item added successfully&messageType=success");
    }

    private void updateItem(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        InventoryItem item = inventoryService.getInventoryItem(id);

        item.setName(request.getParameter("name"));
        item.setCategory(request.getParameter("category"));
        item.setQuantity(Double.parseDouble(request.getParameter("quantity")));
        item.setUnit(request.getParameter("unit"));
        item.setThreshold(Double.parseDouble(request.getParameter("threshold")));
        item.setSupplier(request.getParameter("supplier"));

        inventoryService.updateInventoryItem(item);

        response.sendRedirect("inventory?message=Item updated successfully&messageType=success");
    }

    private void deleteItem(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        inventoryService.deleteInventoryItem(id);

        response.sendRedirect("inventory?message=Item deleted successfully&messageType=success");
    }
}