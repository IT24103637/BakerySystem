package com.BakeryOrder.dto;

public class ProductDTO {
    private String category;
    private String number;
    private String name;
    private String price;
    private String image;

    public ProductDTO(String category, String number, String name, String price, String image) {
        this.category = category;
        this.number = number;
        this.name = name;
        this.price = price;
        this.image = image;
    }

    public String getCategory() { return category; }
    public String getNumber() { return number; }
    public String getName() { return name; }
    public String getPrice() { return price; }
    public String getImage() { return image; }

    public void setName(String name) { this.name = name; }
    public void setPrice(String price) { this.price = price; }
    public void setImage(String image) { this.image = image;}
    public void setCategory(String category) { this.category = category; }
    public void setNumber(String number) { this.number = number; }

    public Object getImagePath() {
        return image;
    }
}
