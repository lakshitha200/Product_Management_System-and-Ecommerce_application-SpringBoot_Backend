package com.PMS.PMS.Repository;

import com.PMS.PMS.Model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin,Long> {
    Optional<Admin> findByUsername(String username);

}
