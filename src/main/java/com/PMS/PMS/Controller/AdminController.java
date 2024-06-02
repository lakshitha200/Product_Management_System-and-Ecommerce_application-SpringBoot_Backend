package com.PMS.PMS.Controller;

import com.PMS.PMS.Dto.AdminRoleDto;
import com.PMS.PMS.Model.Admin;
import com.PMS.PMS.Service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1")
public class AdminController {

    @Autowired
    private AdminService adminService;

    // Get all Admins
    @GetMapping("/admins")
    @PreAuthorize("hasRole('ADMIN') or hasRole('ADMIN_MANAGER')")
    public List<Admin> getAllAdmins(){
        return adminService.getAllAdmins();
    }

    // Create an Admin
    @PostMapping("/admins")
    @PreAuthorize("hasRole('ADMIN_MANAGER')")
    public ResponseEntity<Admin> createAdmin(@RequestBody AdminRoleDto adminRoleDto){
        return new ResponseEntity<>(adminService.createAdmin(adminRoleDto),HttpStatus.CREATED);
    }


    // Get Admin By Id
    @GetMapping("/admins/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('ADMIN_MANAGER')")
    public ResponseEntity<Admin> getAdminByID(@PathVariable("id") long adminId){
        return new ResponseEntity<Admin>(adminService.getAdminByID(adminId),HttpStatus.OK);
    }

    // Get Logged User Details
    @GetMapping("/admins/active/{username}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('ADMIN_MANAGER')")
    public ResponseEntity<Admin> getAdminByUserName(@PathVariable("username") String username){
        return new ResponseEntity<Admin>(adminService.findByUsername(username),HttpStatus.OK);
    }


    // Update Admin By Id
    @PutMapping("/admins/{id}")
    @PreAuthorize("hasRole('ADMIN_MANAGER')")
    public ResponseEntity<Admin> updateAdmin(@PathVariable("id") long adminId ,@RequestBody AdminRoleDto adminRoleDto){
        return new ResponseEntity<Admin>(adminService.updateAdmin(adminRoleDto,adminId),HttpStatus.OK);
    }

    // Delete Admin By Id
    @DeleteMapping("/admins/{id}")
    @PreAuthorize("hasRole('ADMIN_MANAGER')")
    public ResponseEntity<String> deleteAdmin(@PathVariable("id") long adminId){
        adminService.deleteAdmin(adminId);
        return new ResponseEntity<String>("Deleted",HttpStatus.OK);

    }
}
