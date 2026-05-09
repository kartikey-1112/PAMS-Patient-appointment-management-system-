package com.pams.admin.controller;

import com.pams.admin.DTO.AdminDTO;
import com.pams.admin.DTO.OnLogin;
import com.pams.admin.DTO.OnRegister;
import com.pams.admin.model.Admin;
import com.pams.admin.services.serviceImpl.AdminServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;

@RestController
public class AdminController {

    @Autowired
    AdminServiceImpl adminService;

    @PostMapping("/registerAdmin")
    public ResponseEntity<Map<String, Object>> registerAdmin(@RequestBody @Validated(OnRegister.class) AdminDTO dto) {

        return adminService.registerAdmin(dto);
    }

    @PostMapping("/loginAdmin")
    public ResponseEntity<Map<String, Object>> loginAdmin(@RequestBody @Validated(OnLogin.class) AdminDTO dto) {
        return adminService.loginAdmin(dto);
    }

    @DeleteMapping("/deleteAdmin/{email}")
    public ResponseEntity<Map<String, Object>> deleteAdmin(@PathVariable String email) {
        return adminService.deleteAdmin(email);
    }

    @GetMapping("/fetchAdminByEmail/{email}")
    public ResponseEntity<Map<String, Object>> fetchAdminByEmail(@PathVariable String email) {
        return adminService.fetchAdminByEmail(email);
    }

    @GetMapping("/fetchAllAdmin")
    public ResponseEntity<Map<String, Object>> fetchAllAdmin() {
        return adminService.fetchAllAdmin();

    }
    @GetMapping("/fetchSystemLogs")
    public ResponseEntity<Map<String,Object>> fetchSystemLogs() throws IOException {
        return adminService.fetchSystemLogs();
    }
}
