package com.BakeryOrder.dao;

import com.BakeryOrder.dto.AdminDTO;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class AdminDAO {
    private final String filePath;

    public AdminDAO(String filePath) {
        this.filePath = filePath;
        initializeFile();
    }

    private void initializeFile() {
        File file = new File(filePath);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.err.println("Error creating admin data file: " + e.getMessage());
            }
        }
    }

    public List<AdminDTO> getAllAdmins() {
        List<AdminDTO> admins = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    admins.add(new AdminDTO(parts[0], parts[1], parts[2]));
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading admin data: " + e.getMessage());
        }
        return admins;
    }

    public AdminDTO getAdminByUsername(String username) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 1 && parts[0].equals(username)) {
                    return new AdminDTO(parts[0], parts[1], parts.length > 2 ? parts[2] : "");
                }
            }
        } catch (IOException e) {
            System.err.println("Error finding admin: " + e.getMessage());
        }
        return null;
    }

    public boolean saveAdmin(AdminDTO admin) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.write(admin.getUsername() + "," + admin.getPassword() + "," + admin.getFirstname());
            writer.newLine();
            return true;
        } catch (IOException e) {
            System.err.println("Error saving admin: " + e.getMessage());
            return false;
        }
    }

    public boolean updateAdmins(List<AdminDTO> admins) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (AdminDTO admin : admins) {
                writer.write(admin.getUsername() + "," + admin.getPassword() + "," + admin.getFirstname());
                writer.newLine();
            }
            return true;
        } catch (IOException e) {
            System.err.println("Error updating admins: " + e.getMessage());
            return false;
        }
    }

    public boolean deleteAdmin(String username) {
        List<AdminDTO> admins = getAllAdmins();
        List<AdminDTO> updatedAdmins = new ArrayList<>();

        for (AdminDTO admin : admins) {
            if (!admin.getUsername().equals(username)) {
                updatedAdmins.add(admin);
            }
        }

        if (admins.size() != updatedAdmins.size()) {
            return updateAdmins(updatedAdmins);
        }
        return false;
    }
}