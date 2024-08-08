package com.itzcorpio.spring_em_sys.service;

import com.itzcorpio.spring_em_sys.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
}
