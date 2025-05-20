package lk.sliit.eleven.bakeryorder.controllers;

import lk.sliit.eleven.bakeryorder.services.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "HomeController", urlPatterns = {"/home", ""})
public class HomeController extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
    private final OrderService orderService = new OrderService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Get statistics for dashboard
            request.setAttribute("totalOrders", orderService.getAllOrdersCount());
            request.setAttribute("pendingOrders", orderService.getPendingOrdersCount());
            request.setAttribute("processingOrder", orderService.getCurrentlyProcessingOrder());
            request.setAttribute("completedToday", orderService.getCompletedTodayCount());

            // Get today's orders
            request.setAttribute("todaysOrders", orderService.getTodayOrders());

            request.getRequestDispatcher("/views/home.jsp").forward(request, response);
        } catch (Exception e) {
            logger.error("Error in HomeController: ", e);
            request.setAttribute("error", "Failed to load dashboard data");
            response.sendRedirect(request.getContextPath() + "/error");
        }
    }
}