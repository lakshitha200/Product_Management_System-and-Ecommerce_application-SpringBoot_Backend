package com.PMS.PMS.Controller;

import com.PMS.PMS.Dto.AdminRoleDto;
import com.PMS.PMS.Dto.CustomerSignUpDto;
import com.PMS.PMS.Model.Admin;
import com.PMS.PMS.Model.Customer;
import com.PMS.PMS.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;


    @GetMapping()
    public List<Customer> getAllCustomers(){
        return customerService.getAllCustomers();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<Customer> getCustomersById(@PathVariable("id") long id){
        return new ResponseEntity<>(customerService.getCustomersById(id),HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<Customer> updateCustomer(@PathVariable("id") long id ,@RequestBody CustomerSignUpDto customerSignUpDto){
        return new ResponseEntity<Customer>(customerService.updateCustomer(customerSignUpDto,id),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('CUSTOMER')")
    public String deleteCustomer(@PathVariable("id") long id){
        return customerService.deleteCustomer(id);
    }



}
