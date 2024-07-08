package com.example.online_shop.dto;

import com.example.online_shop.models.Category;
import com.example.online_shop.models.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class ProductDto {

    @Size(min = 3, max = 100, message = "Name must be more than 3 characters!")
    @NotBlank(message = "Name is mandatory")
    private String name;

    private String imagePath = "/assets/upload/default-image.jpeg";

    @Positive(message = "Product can't be free")
    private int price;

    @Positive(message = "Inventory can't be empty")
    private int stock;

    private int inventory;

    private int categoryId;

    private Category category;

    private User seller;

    private String description = "default description";

    // constructor, getters, and setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public User getSeller() {
        return seller;
    }

    public void setSeller(User seller) {
        this.seller = seller;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getInventory() {
        return inventory;
    }

    public void setInventory(int inventory) {
        this.inventory = inventory;
    }
}
