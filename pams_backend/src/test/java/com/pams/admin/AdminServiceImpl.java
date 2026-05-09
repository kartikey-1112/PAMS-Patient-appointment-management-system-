package com.pams.admin;

import com.pams.admin.DTO.AdminDTO;

import com.pams.admin.model.Admin;

import com.pams.admin.repository.AdminRepo;

import com.pams.admin.services.serviceImpl.AdminServiceImpl;
import com.pams.security.PasswordHashing;

import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;

import org.mockito.*;

import org.springframework.http.ResponseEntity;

import java.io.IOException;

import java.nio.charset.StandardCharsets;

import java.nio.file.Files;

import java.nio.file.Path;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.*;

class AdminServiceImplTest {

    @Mock

    private AdminRepo adminRepo;

    @InjectMocks

    private AdminServiceImpl adminService;

    private AdminDTO adminDTO;

    private Admin admin;

    @BeforeEach
    void setUp() {

        MockitoAnnotations.openMocks(this);

        adminDTO = new AdminDTO();

        adminDTO.setEmail("admin@example.com");

        adminDTO.setPassword("securePass");

        admin = new Admin();

        admin.setAdminId(1);

        admin.setEmail("admin@example.com");

        admin.setPassword(PasswordHashing.hashPassword("securePass"));

    }

    @Test
    void testRegisterAdmin_AlreadyExists() {

        when(adminRepo.findByEmail(adminDTO.getEmail())).thenReturn(Optional.of(admin));

        ResponseEntity<Map<String, Object>> response = adminService.registerAdmin(adminDTO);

        assertEquals(400, response.getStatusCodeValue());

        assertEquals("admin is already registred", response.getBody().get("message"));

    }

    @Test
    void testRegisterAdmin_Success() {

        when(adminRepo.findByEmail(adminDTO.getEmail())).thenReturn(Optional.empty());

        when(adminRepo.save(any(Admin.class))).thenReturn(admin);

        ResponseEntity<Map<String, Object>> response = adminService.registerAdmin(adminDTO);

        assertEquals(201, response.getStatusCodeValue());

        assertEquals("admin is created successfully", response.getBody().get("message"));

    }

    @Test
    void testLoginAdmin_InvalidEmail() {

        when(adminRepo.findByEmail(adminDTO.getEmail())).thenReturn(Optional.empty());

        ResponseEntity<Map<String, Object>> response = adminService.loginAdmin(adminDTO);

        assertEquals(401, response.getStatusCodeValue());

        assertEquals("Invalid Admin", response.getBody().get("message"));

    }

    @Test
    void testLoginAdmin_InvalidPassword() {

        Admin wrongPasswordAdmin = new Admin();

        wrongPasswordAdmin.setEmail(adminDTO.getEmail());

        wrongPasswordAdmin.setPassword(PasswordHashing.hashPassword("wrongPass"));

        when(adminRepo.findByEmail(adminDTO.getEmail())).thenReturn(Optional.of(wrongPasswordAdmin));

        ResponseEntity<Map<String, Object>> response = adminService.loginAdmin(adminDTO);

        assertEquals(401, response.getStatusCodeValue());

        assertEquals("Invalid Password", response.getBody().get("message"));

    }

//    @Test
//    void testLoginAdmin_Success() {
//        // 1. Arrange: Ensure adminDTO and admin are initialized
//        // adminDTO must contain the plain-text password
//        // admin must contain the hashed password (or a value that verifies)
//
//        // 2. Mock the static PasswordHashing.verifyPassword() call
//        try (MockedStatic<PasswordHashing> mockedHashing = mockStatic(PasswordHashing.class)) {
//
//            // When the static verifyPassword is called, force it to return true
//            mockedHashing.when(
//                    () -> PasswordHashing.verifyPassword(
//                            anyString(), // The plain-text password from adminDTO
//                            anyString()  // The hashed password from admin (mocked)
//                    )
//            ).thenReturn(true);
//
//            // Mock the repository call to return the admin object
//            when(adminRepo.findByEmail(adminDTO.getEmail())).thenReturn(Optional.of(admin));
//
//            // 3. Act
//            ResponseEntity<Map<String, Object>> response = adminService.loginAdmin(adminDTO);
//
//            // 4. Assert
//            // Now the service will successfully bypass the password check and return 202
//            assertEquals(202, response.getStatusCodeValue());
//            assertNotNull(response.getBody().get("data"));
//
//            // Optional: Verify that the static method was actually called
//            mockedHashing.verify(() -> PasswordHashing.verifyPassword(
//                    adminDTO.getPassword(),
//                    admin.getPassword()
//            ), times(1));
//        }
//
//    }

    @Test
    void testDeleteAdmin_NotFound() {

        when(adminRepo.findByEmail(adminDTO.getEmail())).thenReturn(Optional.empty());

        ResponseEntity<Map<String, Object>> response = adminService.deleteAdmin(adminDTO.getEmail());

        assertEquals(404, response.getStatusCodeValue());

        assertEquals("admin not found", response.getBody().get("message"));

    }

    @Test
    void testDeleteAdmin_Success() {

        when(adminRepo.findByEmail(adminDTO.getEmail())).thenReturn(Optional.of(admin));

        ResponseEntity<Map<String, Object>> response = adminService.deleteAdmin(adminDTO.getEmail());

        assertEquals(200, response.getStatusCodeValue());

        assertEquals("admin deleted successfully", response.getBody().get("message"));

    }

    @Test
    void testFetchAdminByEmail_NotFound() {

        when(adminRepo.findByEmail(adminDTO.getEmail())).thenReturn(Optional.empty());

        ResponseEntity<Map<String, Object>> response = adminService.fetchAdminByEmail(adminDTO.getEmail());

        assertEquals(401, response.getStatusCodeValue());

        assertEquals("Admin unavailable", response.getBody().get("message"));

    }

    @Test
    void testFetchAdminByEmail_Success() {

        when(adminRepo.findByEmail(adminDTO.getEmail())).thenReturn(Optional.of(admin));

        ResponseEntity<Map<String, Object>> response = adminService.fetchAdminByEmail(adminDTO.getEmail());

        assertEquals(200, response.getStatusCodeValue());

        assertEquals("Admin is fetched by email", response.getBody().get("message"));

    }

    @Test
    void testFetchAllAdmin_EmptyList() {

        when(adminRepo.findAll()).thenReturn(Collections.emptyList());

        ResponseEntity<Map<String, Object>> response = adminService.fetchAllAdmin();

        assertEquals(400, response.getStatusCodeValue());

        assertEquals("No admin found", response.getBody().get("message"));

    }

    @Test
    void testFetchAllAdmin_Success() {

        when(adminRepo.findAll()).thenReturn(List.of(admin));

        ResponseEntity<Map<String, Object>> response = adminService.fetchAllAdmin();

        assertEquals(200, response.getStatusCodeValue());

        assertEquals("Here are all admins", response.getBody().get("message"));

    }

    @Test
    void testFetchSystemLogs_Success() throws IOException {

        String mockLogContent = "INFO: System started";

        Path mockPath = Path.of("logs/app.log");

        try (MockedStatic<Files> filesMock = mockStatic(Files.class)) {

            filesMock.when(() -> Files.readString(mockPath, StandardCharsets.UTF_8)).thenReturn(mockLogContent);

            adminService.logFilePath = "logs/app.log";

            ResponseEntity<Map<String, Object>> response = adminService.fetchSystemLogs();

            assertEquals(200, response.getStatusCodeValue());

            assertEquals("System logs fetched successfully", response.getBody().get("message"));

            assertEquals(mockLogContent, response.getBody().get("data"));

        }

    }

}

