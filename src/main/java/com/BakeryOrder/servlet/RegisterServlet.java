package com.bakerycustomer.controller;

import com.bakerycustomer.model.Customer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "RegisterServlet", urlPatterns = {"/register"})
public class RegisterServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.addHeader("Access-Control-Allow-Origin", "*");

        HttpSession session = request.getSession();

        String name = request.getParameter("name");
        String mobile = request.getParameter("mobile");

        if (name.isEmpty()){
            response.getWriter().write("Please enter a name!");
        }else if(mobile.isEmpty()){
            response.getWriter().write("Please enter a mobile!");
        }else if(!mobile.matches("^(?:\\+94|94|0)(70|71|72|75|76|77|78)\\d{7}$")){
            response.getWriter().write("Invalid mobile number!");
        }else{

            Customer customer = new Customer();
            customer.setName(name);
            customer.setMobile(mobile);

            List<Customer> customerList = (List) session.getAttribute("customerList");

            if (customerList == null) {
                customerList = new ArrayList<Customer>();
            }

            customerList.add(customer);
            session.setAttribute("customerList", customerList);

            response.getWriter().write("Registration Successful");

        }

        System.out.println("done" + name +" " + mobile);
    }

}
