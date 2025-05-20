package lk.sliit.eleven.bakeryorder.controllers;

import lk.sliit.eleven.bakeryorder.model.Order;
import lk.sliit.eleven.bakeryorder.model.OrderItem;
import lk.sliit.eleven.bakeryorder.services.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "OrderController", urlPatterns = {"/orders/*"})
public class OrderController extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);
    private final OrderService orderService = new OrderService();
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getPathInfo();

        try {
            if (action == null || action.equals("/")) {
                listOrders(request, response);
            } else {
                switch (action) {
                    case "/list":
                        listOrders(request, response);
                        break;
                    case "/create":
                        showCreateForm(request, response);
                        break;
                    case "/details":
                        showOrderDetails(request, response);
                        break;
                    case "/cancel":
                        cancelOrder(request, response);
                        break;
                    default:
                        response.sendError(HttpServletResponse.SC_NOT_FOUND);
                }
            }
        } catch (Exception e) {
            logger.error("Error in OrderController doGet: ", e);
            handleError(request, response, e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getPathInfo();

        if (action != null && action.equals("/create")) {
            createOrder(request, response);
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    private void listOrders(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            List<Order> orders = orderService.getAllOrders();
            request.setAttribute("orders", orders);
            request.setAttribute("orderService", orderService);
            request.getRequestDispatcher("/views/orders.jsp").forward(request, response);
        } catch (Exception e) {
            throw new ServletException("Failed to retrieve orders", e);
        }
    }

    private void showCreateForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/views/create-order.jsp").forward(request, response);
    }

    private void showOrderDetails(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String id = request.getParameter("id");
            if (id == null || id.isEmpty()) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing order ID");
                return;
            }

            Order order = orderService.getOrderById(id);
            if (order == null) {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Order not found");
                return;
            }

            request.setAttribute("order", order);
            request.getRequestDispatcher("/views/order-details.jsp").forward(request, response);
        } catch (Exception e) {
            throw new ServletException("Failed to retrieve order details", e);
        }
    }

    private void cancelOrder(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String id = request.getParameter("id");
            if (id == null || id.isEmpty()) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing order ID");
                return;
            }

            orderService.cancelOrder(id);
            request.getSession().setAttribute("message", "Order cancelled successfully");
            response.sendRedirect(request.getContextPath() + "/orders/list");
        } catch (Exception e) {
            throw new ServletException("Failed to cancel order", e);
        }
    }

    private void createOrder(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Extract and validate parameters
            String customerName = request.getParameter("customerName");
            String contactNumber = request.getParameter("contactNumber");
            String pickupTimeStr = request.getParameter("pickupTime");
            String specialInstructions = request.getParameter("specialInstructions");

            if (customerName == null || customerName.isEmpty() ||
                    contactNumber == null || contactNumber.isEmpty() ||
                    pickupTimeStr == null || pickupTimeStr.isEmpty()) {
                throw new IllegalArgumentException("Required fields are missing");
            }

            LocalDateTime pickupTime = LocalDateTime.parse(pickupTimeStr, formatter);

            // Process order items
            List<OrderItem> items = extractOrderItems(request);

            if (items.isEmpty()) {
                throw new IllegalArgumentException("At least one order item is required");
            }

            // Create the order
            String orderId = orderService.createOrder(
                    customerName,
                    contactNumber,
                    pickupTime,
                    items,
                    specialInstructions
            );

            // Redirect to order details
            response.sendRedirect(request.getContextPath() + "/orders/details?id=" + orderId);
        } catch (DateTimeParseException e) {
            request.setAttribute("error", "Invalid date format. Please use YYYY-MM-DDTHH:MM");
            request.getRequestDispatcher("/views/create-order.jsp").forward(request, response);
        } catch (IllegalArgumentException e) {
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher("/views/create-order.jsp").forward(request, response);
        } catch (Exception e) {
            logger.error("Error creating order: ", e);
            throw new ServletException("Failed to create order", e);
        }
    }

    private List<OrderItem> extractOrderItems(HttpServletRequest request) {
        List<OrderItem> items = new ArrayList<>();
        int i = 0;

        while (request.getParameter("items[" + i + "].productId") != null) {
            String productId = request.getParameter("items[" + i + "].productId");
            String productName = getProductName(productId);
            double price = getProductPrice(productId);
            int quantity = Integer.parseInt(request.getParameter("items[" + i + "].quantity"));

            items.add(new OrderItem(productId, productName, quantity, price));
            i++;
        }

        return items;
    }

    private String getProductName(String productId) {
        return productId;
        // Implementation from your product catalog
        // ...
    }

    private double getProductPrice(String productId) {
        // Implementation from your product catalog
        // ...
        return 0;
    }

    private void handleError(HttpServletRequest request, HttpServletResponse response, Exception e)
            throws ServletException, IOException {
        logger.error("Error in OrderController: ", e);
        request.setAttribute("error", e.getMessage());
        request.getRequestDispatcher("/views/error.jsp").forward(request, response);
    }
}