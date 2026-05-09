package com.pams.admin.model;

import com.pams.security.PasswordHashing;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.Length;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Entity
@Table(name = "admins")
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int adminId;

    @NotNull(message = "Name should not be NULL")
    @Size(min = 3, max = 20, message = "Name should be more than 3 words and must be less than 20 words")
    private String name;

    @Column(nullable = false, length = 100, unique = true)
    @NotEmpty(message = "email must have something")
    @Email
    private String email;

    @Column(nullable = false, length = 100)
    @Length(max = 10, min = 10, message = "Number should be of ten digit")
    @Pattern(regexp = "^[0-9]{10}$", message = "Phone number must be digit")
    private String phone;

    @Column(nullable = false, length = 100)
    @NotBlank(message = "Password should not be null")
    private String password;

    @Column(nullable = false, length = 100)
    @Pattern(regexp = "^[A-Za-z ]+$", message = "Role does not contain digits")
    private String role = "ADMIN";

    public Admin() {}

    public Admin(String name, String email, String phone, String password, String role) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.role = role;
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

        this.password = PasswordHashing.hashPassword(password);
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }
}