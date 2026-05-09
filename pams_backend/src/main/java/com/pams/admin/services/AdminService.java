package com.pams.admin.services;

import com.pams.admin.DTO.AdminDTO;
import com.pams.admin.model.Admin;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.Map;

public interface AdminService {
    public ResponseEntity<Map<String, Object>> registerAdmin(AdminDTO admin);

    public ResponseEntity<Map<String, Object>> loginAdmin(AdminDTO admin);

    public ResponseEntity<Map<String, Object>> deleteAdmin(String email);

    public ResponseEntity<Map<String, Object>> fetchAdminByEmail(String email);

    public ResponseEntity<Map<String, Object>> fetchAllAdmin();

    public ResponseEntity<Map<String,Object>> fetchSystemLogs() throws IOException;
}
