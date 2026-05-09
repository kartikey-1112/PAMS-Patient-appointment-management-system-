package com.pams.admin.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class AdminDTO {
    private int adminId;
    @NotBlank(message = "Name is required", groups = OnRegister.class)
    private String name;

    @NotBlank(message = "Email is required", groups = {OnRegister.class, OnLogin.class})
    @Email(message = "Invalid email format", groups = {OnRegister.class, OnLogin.class})
    private String email;

    @NotBlank(message = "Password is required", groups = {OnRegister.class, OnLogin.class})
    private String password;

    @NotBlank(message = "Phone number is required", groups = OnRegister.class)
    private String phone;

    @NotBlank(message = "Role is required", groups = OnRegister.class)
    private String role = "ADMIN";

    public AdminDTO(String name, String email, String password, String phone, String role) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.role = role;
    }

    public AdminDTO() {

    }

    public AdminDTO(int adminId, String name, String email, String password, String phone, String role) {
        this.adminId = adminId;
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }

    @Override
    public String toString() {
        return "AdminDTO{" +
                "adminId=" + adminId +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
