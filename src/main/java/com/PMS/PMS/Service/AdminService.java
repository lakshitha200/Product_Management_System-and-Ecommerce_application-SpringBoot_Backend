package com.PMS.PMS.Service;


import com.PMS.PMS.Dto.AdminRoleDto;
import com.PMS.PMS.Model.Admin;

import java.util.List;

public interface AdminService {
    public List<Admin> getAllAdmins();
    public Admin createAdmin(AdminRoleDto adminRoleDto);

    Admin getAdminByID(long adminId);

    Admin updateAdmin(AdminRoleDto adminRoleDto, long adminId);

    void deleteAdmin(long adminId);


    Admin findByUsername(String username);
}
