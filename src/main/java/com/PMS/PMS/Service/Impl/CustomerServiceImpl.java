package com.PMS.PMS.Service.Impl;

import com.PMS.PMS.Dto.CustomerSignUpDto;
import com.PMS.PMS.Exception.ResourceNotFoundException;
import com.PMS.PMS.Model.Customer;
import com.PMS.PMS.Repository.CustomerRepository;
import com.PMS.PMS.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl  implements CustomerService{

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;


    //get List
    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
//        customerList.stream()
//                .map(customer -> {
//                    return customer.getName().substring(2);
//                });

//
    }

    //update
    @Override
    public Customer updateCustomer(CustomerSignUpDto customerSignUpDto, long id) {
        Customer exsitingCustomer = customerRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Product is Not Found")
        );
        exsitingCustomer.setCusNumber(customerSignUpDto.getCusNumber());
        exsitingCustomer.setName(customerSignUpDto.getName());
        exsitingCustomer.setUsername(customerSignUpDto.getUsername());
        exsitingCustomer.setLocation(customerSignUpDto.getLocation());
        exsitingCustomer.setEmail(customerSignUpDto.getEmail());
        exsitingCustomer.setPassword(this.passwordEncoder.encode(customerSignUpDto.getPassword()));
        exsitingCustomer.setPhone(customerSignUpDto.getPhone());
        exsitingCustomer.setImg(customerSignUpDto.getImg());

        return customerRepository.save(exsitingCustomer);
    }

    @Override
    public String deleteCustomer(long id) {
        customerRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Product is Not Found")
        );
        customerRepository.deleteById(id);
        return "Successfully Deleted";
    }

    @Override
    public Customer getCustomersById(long id) {
        Optional<Customer> exsitingCustomer = customerRepository.findById(id);
        if (exsitingCustomer.isPresent()) {
            return exsitingCustomer.get();
        } else {
            throw new ResourceNotFoundException("Cannot Find");
        }


    }


}
