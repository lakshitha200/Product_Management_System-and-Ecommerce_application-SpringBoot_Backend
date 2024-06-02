package com.PMS.PMS.Service;

import com.PMS.PMS.Dto.CustomerSignUpDto;
import com.PMS.PMS.Dto.LoginDto;
import com.PMS.PMS.Model.Admin;
import com.PMS.PMS.Model.Customer;

public interface AuthService {
    String login(LoginDto loginDto);

    Customer createcustomer(CustomerSignUpDto customerSignUpDto);

    Customer findCustomerByUsername(String username);

//    Admin findByUsername(String username);
}
