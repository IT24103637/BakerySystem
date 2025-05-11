package com.BakeryOrder.dto;

public class UserDTO {
    private String username;
    private String password;
    private String firstname;

    public UserDTO(String username, String password, String firstname) {
        this.username = username;
        this.password = password;
        this.firstname = firstname;
    }

    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public String getFirstname() { return firstname; }

    public void setPassword(String password) { this.password = password; }
    public void setFirstname(String firstname) { this.firstname = firstname; }

    public Object getFirstName() {
        return firstname;
    }
}
