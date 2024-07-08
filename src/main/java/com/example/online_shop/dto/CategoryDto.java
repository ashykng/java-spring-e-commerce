package com.example.online_shop.dto;

import jakarta.validation.constraints.NotBlank;

public class CategoryDto {

    private int id;

    @NotBlank(message = "category name not added !")
    private String name;


    public CategoryDto(){
    }

    public CategoryDto(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public CategoryDto(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
