package com.itzcorpio.spring_em_sys.config;

import com.itzcorpio.spring_em_sys.model.Role;
import com.itzcorpio.spring_em_sys.model.User;
import com.itzcorpio.spring_em_sys.repository.RoleRepository;
import com.itzcorpio.spring_em_sys.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    @Override
    public void run(String... args) throws Exception {
        if (roleRepository.count() == 0) {
            Role userRole = new Role();
            userRole.setName("user");
            roleRepository.save(userRole);

            Role adminRole = new Role();
            adminRole.setName("admin");
            roleRepository.save(adminRole);
        }

        if (userRepository.count() == 0) {
            Set<Role> roles = new HashSet<>(roleRepository.findAll());

            User user = new User();
            user.setUsername("user");
            user.setPassword(encoder.encode("password"));
            user.setRoles(roles);
            userRepository.save(user);

            User admin = new User();
            admin.setUsername("admin");
            admin.setPassword(encoder.encode("admin"));
            admin.setRoles(roles);
            userRepository.save(admin);
        }
    }

}
