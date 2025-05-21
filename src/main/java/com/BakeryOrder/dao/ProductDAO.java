package com.BakeryOrder.dao;

import com.BakeryOrder.dto.ProductDTO;

import java.io.*;
import java.util.*;

public class ProductDAO {
    private final String filePath;

    public ProductDAO(String filePath) {
        this.filePath = filePath;
    }

    public List<ProductDTO> getAllProducts() {
        List<ProductDTO> list = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length >= 5) {
                    list.add(new ProductDTO(data[0], data[1], data[2], data[3], data[4]));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<ProductDTO> getProductsByCategory(String category) {
        List<ProductDTO> filtered = new ArrayList<>();
        for (ProductDTO p : getAllProducts()) {
            if (p.getCategory().equalsIgnoreCase(category)) {
                filtered.add(p);
            }
        }
        return filtered;
    }

    public void addProduct(ProductDTO p) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath, true))) {
            bw.write(p.getCategory() + "," + p.getNumber() + "," + p.getName() + "," + p.getPrice() + "," + p.getImage());
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void updateAllProducts(List<ProductDTO> products) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            for (ProductDTO p : products) {
                bw.write(p.getCategory() + "," + p.getNumber() + "," + p.getName() + "," + p.getPrice() + "," + p.getImage());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
