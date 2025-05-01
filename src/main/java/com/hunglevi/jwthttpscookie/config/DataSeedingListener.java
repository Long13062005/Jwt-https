package com.hunglevi.jwthttpscookie.config;

import com.hunglevi.jwthttpscookie.entities.Role;
import com.hunglevi.jwthttpscookie.entities.User;
import com.hunglevi.jwthttpscookie.repository.RoleRepository;
import com.hunglevi.jwthttpscookie.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class DataSeedingListener implements ApplicationListener<ContextRefreshedEvent> {
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        // Check if the roles already exist
        if (roleRepository.count() == 0) {
            roleRepository.save(new Role(null, "ROLE_ADMIN", false));
            roleRepository.save(new Role(null, "ROLE_CUSTOMER", false));
        }

        // Check if the admin user already exists
        if (userRepository.count() == 0) {
            // Create and save an admin user
            User adminUser = new User();
            Set<Role> roles = Set.of(roleRepository.findByName("ROLE_ADMIN"), roleRepository.findByName("ROLE_CUSTOMER"));
            adminUser.setUsername("admin");
            adminUser.setPassword(passwordEncoder.encode("admin123"));
            adminUser.setRoles(roles);
            userRepository.save(adminUser);
        }
        if (userRepository.findByUsername("customer") == null) {
            // Create and save an admin user
            User customerUser = new User();
            Set<Role> roles = Set.of(roleRepository.findByName("ROLE_CUSTOMER"));
            customerUser.setUsername("customer");
            customerUser.setPassword(passwordEncoder.encode("customer123"));
            customerUser.setRoles(roles);
            userRepository.save(customerUser);
        }

    }

    @Override
    public boolean supportsAsyncExecution() {
        return ApplicationListener.super.supportsAsyncExecution();
    }
}