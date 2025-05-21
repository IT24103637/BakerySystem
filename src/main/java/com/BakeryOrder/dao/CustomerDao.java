package com.bakerycustomer.dao;

import com.bakerycustomer.model.Customer;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDao {
    private final String FILE_PATH = "src/main/resources/data/customers.txt";

    public void saveCustomer(Customer customer) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            bw.write(customer.getName() + "," + customer.getMobile());
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Customer> getAllCustomers() {
        List<Customer> customers = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    customers.add(new Customer(parts[0], parts[1]));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return customers;
    }

    public void deleteCustomer(String mobile) {
        List<Customer> customers = getAllCustomers();
        customers.removeIf(c -> c.getMobile().equals(mobile));
        saveAll(customers);
    }

    public void updateCustomer(String oldMobile, Customer updatedCustomer) {
        List<Customer> customers = getAllCustomers();
        for (int i = 0; i < customers.size(); i++) {
            if (customers.get(i).getMobile().equals(oldMobile)) {
                customers.set(i, updatedCustomer);
                break;
            }
        }
        saveAll(customers);
    }

    private void saveAll(List<Customer> customers) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Customer c : customers) {
                bw.write(c.getName() + "," + c.getMobile());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
