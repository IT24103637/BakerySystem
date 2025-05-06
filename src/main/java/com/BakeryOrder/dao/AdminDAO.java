package com.BakeryOrder.dao;

import com.BakeryOrder.dto.AdminDTO;

import java.io.*;
import java.util.*;

public class AdminDAO {
    private final String filePath;

    public AdminDAO(String filePath) {
        this.filePath = filePath;
    }

    public List<AdminDTO> getAllAdmins() throws IOException {
        List<AdminDTO> admins = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;

        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",");
            if (parts.length == 3) {
                admins.add(new AdminDTO(parts[0], parts[1], parts[2]));
            }
        }

        reader.close();
        return admins;
    }

    public AdminDTO getAdminByUsername(String username) throws IOException {
        for (AdminDTO admin : getAllAdmins()) {
            if (admin.getUsername().equals(username)) return admin;
        }
        return null;
    }

    public void saveAdmin(AdminDTO admin) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true));
        writer.write(admin.getUsername() + "," + admin.getPassword() + "," + admin.getFirstname());
        writer.newLine();
        writer.close();
    }

    public void updateAdmins(List<AdminDTO> admins) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
        for (AdminDTO admin : admins) {
            writer.write(admin.getUsername() + "," + admin.getPassword() + "," + admin.getFirstname());
            writer.newLine();
        }
        writer.close();
    }
}
