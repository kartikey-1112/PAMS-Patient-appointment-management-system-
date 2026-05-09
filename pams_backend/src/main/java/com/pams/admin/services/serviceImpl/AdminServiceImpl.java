package com.pams.admin.services.serviceImpl;

import com.pams.admin.DTO.AdminDTO;
import com.pams.admin.DTO.AdminMapper;
import com.pams.admin.model.Admin;
import com.pams.admin.repository.AdminRepo;
import com.pams.admin.services.AdminService;
import com.pams.security.PasswordHashing;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

@Service
@Validated
public class AdminServiceImpl implements AdminService {

    @Value("${logging.file.name:logs/app.log}")
    public String logFilePath;
    private static final Logger log = LoggerFactory.getLogger(AdminServiceImpl.class);

    @Autowired
    AdminRepo adminRepo;


    @Override
    public ResponseEntity<Map<String, Object>> registerAdmin(AdminDTO adminDTO) {
        log.info("Attempting to register new admin with email: {}", adminDTO.getEmail());
        Map<String, Object> response = new HashMap<>();

        Optional<Admin> checkPresent = adminRepo.findByEmail(adminDTO.getEmail());
        if (!checkPresent.isEmpty()) {
            response.put("message", "admin is already registred");
            response.put("data", adminDTO);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        Admin admin = AdminMapper.toEntity(adminDTO);

        Admin adminData = adminRepo.save(admin);


        log.info("Admin created successfully with ID: {}", adminData.getAdminId());
        response.put("message", "admin is created successfully");
        response.put("data", AdminMapper.toDTO(adminData));
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Map<String, Object>> loginAdmin(AdminDTO adminDTO) {
        log.info("Attempting login for admin email: {}", adminDTO.getEmail());
        Map<String, Object> response = new HashMap<>();
        Admin admin = AdminMapper.toEntity(adminDTO);
        Optional<Admin> adminDB = adminRepo.findByEmail(adminDTO.getEmail());

        if (adminDB.isEmpty()) {
            log.warn("Login failed: Admin email not found in the database: {}", admin.getEmail());
            response.put("message", "Invalid Admin");
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        }

        Admin adminDetail = adminDB.get();
        if (!PasswordHashing.verifyPassword(adminDTO.getPassword(),adminDB.get().getPassword())) {
            log.warn("Login failed: Invalid password for admin: {}", admin.getEmail());
            response.put("message", "Invalid Password");
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        }

        log.info("Admin successfully logged in: {}", admin.getEmail());
        response.put("data", AdminMapper.toDTO(adminDetail));
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

    @Override
    public ResponseEntity<Map<String, Object>> deleteAdmin(String email) {
        log.info("Attempting to delete admin with email: {}", email);
        Map<String, Object> response = new HashMap<>();
        Optional<Admin> adminDB = adminRepo.findByEmail(email);

        if (adminDB.isEmpty()) {
            log.warn("Deletion failed: Admin not found for email: {}", email);
            response.put("message", "admin not found");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        Admin adminToDelete = adminDB.get();
        adminRepo.delete(adminToDelete);

        log.info("Admin successfully deleted: {}", email);
        response.put("message", "admin deleted successfully");
        response.put("data", adminToDelete);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Map<String, Object>> fetchAdminByEmail(String email) {
        log.debug("Fetching admin by email: {}", email);
        Map<String, Object> response = new HashMap<>();
        Optional<Admin> fetchAdmin = adminRepo.findByEmail(email);

        if (fetchAdmin.isEmpty()) {
            log.info("Fetch failed: Admin unavailable with email: {}", email);
            response.put("message", "Admin unavailable");
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        }

        log.debug("Successfully fetched admin: {}", email);
        response.put("message", "Admin is fetched by email");
        response.put("data", fetchAdmin.get());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Map<String, Object>> fetchAllAdmin() {
        log.info("Fetching all administrators.");
        Map<String, Object> response = new HashMap<>();
        List<Admin> allAdmin = adminRepo.findAll();

        if (allAdmin.isEmpty()) {
            log.warn("No administrators found in the database.");
            response.put("message", "No admin found");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        log.info("Successfully retrieved {} administrators.", allAdmin.size());
        response.put("message", "Here are all admins");
        response.put("data", allAdmin);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Map<String, Object>> fetchSystemLogs() throws IOException {
        log.info("Reading system logs from file path: {}", logFilePath);
        Map<String, Object> response = new HashMap<>();
        String logContent = Files.readString(Paths.get(logFilePath), StandardCharsets.UTF_8);
        log.info("Successfully read log file. Size: {} characters.", logContent.length());
        response.put("message", "System logs fetched successfully");
        response.put("data", logContent);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}