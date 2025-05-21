package com.bakerycustomer.model;

public class Customer implements java.io.Serializable {

    private String name;
    private String mobile;

    public Customer() {}

    public Customer(String name, String mobile) {
        this.name = name;
        this.mobile = mobile;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getMobile() {
        return mobile;
    }
}
