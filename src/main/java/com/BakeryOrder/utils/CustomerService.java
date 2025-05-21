package com.bakerycustomer.service;

import com.bakerycustomer.dao.CustomerDao;
import com.bakerycustomer.model.Customer;

import java.util.List;

public class CustomerService {
    private CustomerDao dao = new CustomerDao();

    public void addCustomer(Customer customer) {
        dao.saveCustomer(customer);
    }

    public List<Customer> getAllCustomers() {
        return dao.getAllCustomers();
    }

    public void removeCustomer(String mobile) {
        dao.deleteCustomer(mobile);
    }

    public void updateCustomer(String oldMobile, Customer updatedCustomer) {
        dao.updateCustomer(oldMobile, updatedCustomer);
    }

    public Customer getCustomerByMobile(String mobile) {
        List<Customer> customers = dao.getAllCustomers();
        for (Customer c : customers) {
            if (c.getMobile().equals(mobile)) {
                return c;
            }
        }
        return null;
    }
}
