package com.PMS.PMS.Service.Impl;


import com.PMS.PMS.Config.JwtTokenProvider;
import com.PMS.PMS.Dto.CustomerSignUpDto;
import com.PMS.PMS.Dto.LoginDto;
import com.PMS.PMS.Exception.ResourceNotFoundException;
import com.PMS.PMS.Model.Admin;
import com.PMS.PMS.Model.Customer;
import com.PMS.PMS.Repository.AdminRepository;
import com.PMS.PMS.Repository.CustomerRepository;
import com.PMS.PMS.Service.AuthService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public String login(LoginDto loginDto) {

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDto.getUsername(),
                loginDto.getPassword()
        ));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtTokenProvider.generateToken(authentication);

        return token;
    }

    //Save
    @Override
    public Customer createcustomer(CustomerSignUpDto customerSignUpDto) {
        String encodedPassword = passwordEncoder.encode(customerSignUpDto.getPassword());
//        String username = "C_"+customerSignUpDto.getUsername();
        Customer newCustomer = new Customer(
                customerSignUpDto.getCId(),
                customerSignUpDto.getCusNumber(),
                customerSignUpDto.getName(),
                customerSignUpDto.getUsername(),
                customerSignUpDto.getLocation(),
                customerSignUpDto.getEmail(),
                encodedPassword,
                customerSignUpDto.getPhone(),
                customerSignUpDto.getImg(),
                customerSignUpDto.getRole()
        );
        return customerRepository.save(newCustomer);
    }

    @Override
    public Customer findCustomerByUsername(String username) {
        Optional<Customer> exsitingCustomer = customerRepository.findByUsername(username);
        if(exsitingCustomer.isPresent()){
            return exsitingCustomer.get();
        }else {
            throw new ResourceNotFoundException("Cannot Find");
        }
    }

//    // Get Active Admin Data
//    @Override
//    public Admin findByUsername(String username) {
//        Optional<Admin> exsitingAdmin = adminRepository.findByUsername(username);
//        if(exsitingAdmin.isPresent()){
//            return exsitingAdmin.get();
//        }else {
//            throw new ResourceNotFoundException("Cannot Find");
//        }
//    }
}
