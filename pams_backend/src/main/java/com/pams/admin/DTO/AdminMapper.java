package com.pams.admin.DTO;

import com.pams.admin.model.Admin;

public class AdminMapper {
    public static Admin toEntity(AdminDTO adminDTO) {
        Admin admin = new Admin();
        admin.setName(adminDTO.getName());
        admin.setPassword(adminDTO.getPassword());
        admin.setEmail(adminDTO.getEmail());
        admin.setPhone(adminDTO.getPhone());
        admin.setRole(adminDTO.getRole());
        return admin;
    }
    public static AdminDTO toDTO(Admin admin) {

        AdminDTO adminDTO = new AdminDTO();
        adminDTO.setAdminId(admin.getAdminId());
        adminDTO.setName(admin.getName());
        adminDTO.setEmail(admin.getEmail());
        adminDTO.setPhone(admin.getPhone());
        adminDTO.setRole(admin.getRole());
        adminDTO.setPassword(admin.getPassword());

        return adminDTO;
    }
}
