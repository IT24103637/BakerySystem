package com.bakerycustomer.controller;

import com.bakerycustomer.model.Customer;
import com.bakerycustomer.service.CustomerService;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

public class CustomerController extends HttpServlet {
    private CustomerService service = new CustomerService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        if ("add".equals(action)) {
            String name = req.getParameter("name");
            String mobile = req.getParameter("mobile");
            service.addCustomer(new Customer(name, mobile));
            resp.sendRedirect("CustomerController?action=list");

        } else if ("update".equals(action)) {
            String oldMobile = req.getParameter("oldMobile");
            String name = req.getParameter("name");
            String mobile = req.getParameter("mobile");
            service.updateCustomer(oldMobile, new Customer(name, mobile));
            resp.sendRedirect("CustomerController?action=list");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        if ("delete".equals(action)) {
            String mobile = req.getParameter("mobile");
            service.removeCustomer(mobile);
            resp.sendRedirect("CustomerController?action=list");

        } else if ("list".equals(action)) {
            List<Customer> customers = service.getAllCustomers();
            req.setAttribute("customers", customers);
            RequestDispatcher dispatcher = req.getRequestDispatcher("customers.jsp");
            dispatcher.forward(req, resp);
        }
    }


}


