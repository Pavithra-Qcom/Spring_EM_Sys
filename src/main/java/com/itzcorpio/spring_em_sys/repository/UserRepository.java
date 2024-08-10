package com.itzcorpio.spring_em_sys.repository;

import com.itzcorpio.spring_em_sys.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
