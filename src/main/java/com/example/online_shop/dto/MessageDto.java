package com.example.online_shop.dto;

public class MessageDto {
    private String name;
    private int phone;
    private String email;

    private String message;

    public MessageDto(){

    }

    public MessageDto(String name, int number, String email, String message) {
        this.name = name;
        this.phone = number;
        this.email = email;
        this.message = message;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
