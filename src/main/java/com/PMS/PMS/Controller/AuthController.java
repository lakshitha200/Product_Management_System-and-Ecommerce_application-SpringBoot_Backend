package com.PMS.PMS.Controller;


import com.PMS.PMS.Dto.CustomerSignUpDto;
import com.PMS.PMS.Dto.JwtAuthResponse;
import com.PMS.PMS.Dto.LoginDto;
import com.PMS.PMS.Model.Admin;
import com.PMS.PMS.Model.Customer;
import com.PMS.PMS.Service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1")
public class AuthController {

    @Autowired
    private AuthService authService;


    // login admin and customer
    @PostMapping("/login")
    public ResponseEntity<JwtAuthResponse> login(@RequestBody LoginDto loginDto){
        String token = authService.login(loginDto);

        JwtAuthResponse jwtAuthResponse = new JwtAuthResponse();
        jwtAuthResponse.setAccessToken(token);

        return new ResponseEntity<>(jwtAuthResponse, HttpStatus.OK);
    }

    //customer signup
    @PostMapping("/sign-up")
    public ResponseEntity<Customer> createCustomer(@RequestBody CustomerSignUpDto customerSignUpDto){
        return new ResponseEntity<>(authService.createcustomer(customerSignUpDto),HttpStatus.CREATED);
    }

    // Get Logged User Details
    @GetMapping("/activeCustomer/{username}")
    public ResponseEntity<Customer> getCustomerByUserName(@PathVariable("username") String username){
        return new ResponseEntity<Customer>(authService.findCustomerByUsername(username),HttpStatus.OK);
    }

//    // Get Logged User Details
//    @GetMapping("/admins/{username}")
//    public ResponseEntity<Admin> getAdminByUserName(@PathVariable("username") String username){
//        return new ResponseEntity<Admin>(authService.findByUsername(username),HttpStatus.OK);
//    }





}

