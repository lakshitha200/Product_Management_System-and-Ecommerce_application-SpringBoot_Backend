package com.PMS.PMS.Service;

import com.PMS.PMS.Dto.CustomerSignUpDto;
import com.PMS.PMS.Model.Customer;

import java.util.List;

public interface CustomerService {

    List<Customer> getAllCustomers();

    Customer updateCustomer(CustomerSignUpDto customerSignUpDto, long id);

    String deleteCustomer(long id);

    Customer getCustomersById(long id);



}
