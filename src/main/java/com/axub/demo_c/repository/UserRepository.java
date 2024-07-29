package com.axub.demo_c.repository;

import com.axub.demo_c.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<AppUser, Long> {
    AppUser findByUsername(String username);
}
