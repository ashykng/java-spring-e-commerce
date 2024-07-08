package com.example.online_shop.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UserDto {

    @Size(min = 3, max = 100, message = "Name must be more than 3 character !")
    @NotBlank(message = "Name is mandatory")
    private String name;

    @Email(message = "Invalid Email !")
    @NotBlank(message = "Email is mandatory")
    private String email;

    @Size(min = 6, message = "Password must be more than 6 character !")
    @NotBlank(message = "Password is mandatory")
    private String password;

    private String address;

    private String role;

    public UserDto(String name, String email, String role) {
        this.name = name;
        this.email = email;
        this.role = role;
    }

    public UserDto(String name, String email, String password, String address, String role) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.address = address;
        this.role = role;
    }

    public UserDto(){

    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
