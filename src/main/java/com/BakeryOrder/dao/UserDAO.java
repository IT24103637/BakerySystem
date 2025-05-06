package com.BakeryOrder.dao;

import com.BakeryOrder.dto.UserDTO;

import java.io.*;
import java.util.*;

public class UserDAO {
    private final String filePath;

    public UserDAO(String filePath) {
        this.filePath = filePath;
    }

    public List<UserDTO> getAllUsers() throws IOException {
        List<UserDTO> users = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;

        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",");
            if (parts.length == 3) {
                users.add(new UserDTO(parts[0], parts[1], parts[2]));
            }
        }

        reader.close();
        return users;
    }

    public UserDTO getUserByUsername(String username) throws IOException {
        for (UserDTO user : getAllUsers()) {
            if (user.getUsername().equals(username)) return user;
        }
        return null;
    }

    public void saveUser(UserDTO user) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true));
        writer.write(user.getUsername() + "," + user.getPassword() + "," + user.getFirstname());
        writer.newLine();
        writer.close();
    }

    public void updateUsers(List<UserDTO> users) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
        for (UserDTO user : users) {
            writer.write(user.getUsername() + "," + user.getPassword() + "," + user.getFirstname());
            writer.newLine();
        }
        writer.close();
    }
}
