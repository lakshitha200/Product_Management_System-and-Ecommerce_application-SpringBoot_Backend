package com.PMS.PMS.Service.Impl;

import com.PMS.PMS.Dto.AdminRoleDto;
import com.PMS.PMS.Exception.ResourceNotFoundException;
import com.PMS.PMS.Model.Admin;
import com.PMS.PMS.Repository.AdminRepository;
import com.PMS.PMS.Service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public List<Admin> getAllAdmins() {
        return adminRepository.findAll();
    }

    @Override
    public Admin createAdmin(AdminRoleDto adminRoleDto) {
        String encodedPassword = passwordEncoder.encode(adminRoleDto.getPassword());
        Admin newAdmin = new Admin(
                adminRoleDto.getId(),
                adminRoleDto.getNumber(),
                adminRoleDto.getName(),
                adminRoleDto.getUsername(),
                adminRoleDto.getEmail(),
                encodedPassword,
                adminRoleDto.getImg(),
                adminRoleDto.getPhone(),
                adminRoleDto.getRoles()
        );
        return adminRepository.save(newAdmin);
    }


    @Override
    public Admin getAdminByID(long adminId) {
        Optional<Admin> exsitingAdmin = adminRepository.findById(adminId);
        if(exsitingAdmin.isPresent()){

            return exsitingAdmin.get();
        }else {
            throw new ResourceNotFoundException("Cannot Find");
        }
    }

    @Override
    public Admin updateAdmin(AdminRoleDto adminRoleDto, long adminId) {
        Admin exsitingAdmin = adminRepository.findById(adminId).orElseThrow(
                () -> new ResourceNotFoundException("Product is Not Found")
        );
        exsitingAdmin.setNumber(adminRoleDto.getNumber());
        exsitingAdmin.setName(adminRoleDto.getName());
        exsitingAdmin.setUsername(adminRoleDto.getUsername());
        exsitingAdmin.setEmail(adminRoleDto.getEmail());
        exsitingAdmin.setPassword(this.passwordEncoder.encode(adminRoleDto.getPassword()));
        exsitingAdmin.setPhone(adminRoleDto.getPhone());
        exsitingAdmin.setImg(adminRoleDto.getImg());
        exsitingAdmin.setRoles(adminRoleDto.getRoles());

        return adminRepository.save(exsitingAdmin);
    }

    @Override
    public void deleteAdmin(long adminId) {
        adminRepository.findById(adminId).orElseThrow(
                () -> new ResourceNotFoundException("Product is Not Found")
        );
        adminRepository.deleteById(adminId);
    }

    // Get Active Admin Data
    @Override
    public Admin findByUsername(String username) {
        Optional<Admin> exsitingAdmin = adminRepository.findByUsername(username);
        if(exsitingAdmin.isPresent()){
            return exsitingAdmin.get();
        }else {
            throw new ResourceNotFoundException("Cannot Find");
        }
    }


}
