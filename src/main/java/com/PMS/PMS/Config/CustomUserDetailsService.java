package com.PMS.PMS.Config;


import com.PMS.PMS.Model.Admin;
import com.PMS.PMS.Model.Customer;
import com.PMS.PMS.Repository.AdminRepository;
import com.PMS.PMS.Repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private AdminRepository adminRepository;
    private CustomerRepository customerRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        if(adminRepository.findByUsername(username).isEmpty()){
            Customer customer = customerRepository.findByUsername(username)
                    .orElseThrow(() -> new UsernameNotFoundException("User not exists by Username or Email"));

            Set<GrantedAuthority> authorities2 = Arrays.stream(customer.getRole().split(","))
                    .map(SimpleGrantedAuthority::new)
                    .collect(Collectors.toSet());
            return new User(
                    username,
                    customer.getPassword(),
                    authorities2
            );
        }else{
            Admin admin = adminRepository.findByUsername(username)
                    .orElseThrow(() -> new UsernameNotFoundException("User not exists by Username or Email"));

            Set<GrantedAuthority> authorities1 = Arrays.stream(admin.getRoles().split(","))
                    .map(SimpleGrantedAuthority::new)
                    .collect(Collectors.toSet());



            return new User(
                    username,
                    admin.getPassword(),
                    authorities1
            );
        }

    }
}