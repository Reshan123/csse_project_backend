package com.HospitalManagementSystem.HospitalManagementSystem.User.service;

import com.HospitalManagementSystem.HospitalManagementSystem.Auth.models.ERole;
import com.HospitalManagementSystem.HospitalManagementSystem.Auth.models.Role;
import com.HospitalManagementSystem.HospitalManagementSystem.Auth.models.User;
import com.HospitalManagementSystem.HospitalManagementSystem.Auth.security.jwt.JwtUtils;
import com.HospitalManagementSystem.HospitalManagementSystem.Auth.security.services.UserDetailsImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class JwtTokenGenerationTest {

    @Autowired
    private JwtUtils jwtUtils;

    private User user;

    @BeforeEach
    void setUp() {
        // Create a sample User object
        user = new User();
        user.setId("1");
        user.setUsername("testUser");
        user.setEmail("test@example.com");
        user.setPassword("password");

        // Create a role and assign it to the user
        Role role = new Role();
        role.setName(ERole.ROLE_ADMIN); // Assuming you have a setName method in Role
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        user.setRoles(roles);
    }

    @Test
    void testGenerateJwtToken() {
        // Build UserDetailsImpl from User
        UserDetailsImpl userDetails = UserDetailsImpl.build(user);

        // Create an Authentication object
        Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

        // Generate JWT token
        String jwtToken = jwtUtils.generateJwtToken(authentication);

        // Assert that the token is not null
        assertNotNull(jwtToken);
        System.out.println("Generated JWT Token: " + jwtToken);
    }
}
