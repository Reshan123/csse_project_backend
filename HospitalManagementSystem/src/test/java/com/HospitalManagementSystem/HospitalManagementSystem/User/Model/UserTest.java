package com.HospitalManagementSystem.HospitalManagementSystem.User.Model;

import com.HospitalManagementSystem.HospitalManagementSystem.Auth.models.ERole;
import com.HospitalManagementSystem.HospitalManagementSystem.Auth.models.Role;
import com.HospitalManagementSystem.HospitalManagementSystem.Auth.models.User;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class UserTest {

    @Test
    public void testUserConstructorAndGetters() {
        // Arrange
        String username = "testUser";
        String email = "test@example.com";
        String password = "Password123";
        String link = "http://example.com/profile";

        // Act
        User user = new User(username, email, password, link);

        // Assert
        assertEquals(username, user.getUsername());
        assertEquals(email, user.getEmail());
        assertEquals(password, user.getPassword());
        assertEquals(link, user.getLink());
        assertNull(user.getId());
        assertNotNull(user.getRoles());
        assertTrue(user.getRoles().isEmpty());
    }

    @Test
    public void testUserSettersAndGetters() {
        // Arrange
        User user = new User();
        String id = "1";
        String username = "testUser";
        String email = "test@example.com";
        String password = "Password123";
        String link = "http://example.com/profile";

        // Act
        user.setId(id);
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(password);
        user.setLink(link);

        // Assert
        assertEquals(id, user.getId());
        assertEquals(username, user.getUsername());
        assertEquals(email, user.getEmail());
        assertEquals(password, user.getPassword());
        assertEquals(link, user.getLink());
    }

    @Test
    public void testRolesSettersAndGetters() {
        User user = new User();
        Role role1 = new Role();
        role1.setName(ERole.ROLE_USER);
        Role role2 = new Role();
        role2.setName(ERole.ROLE_ADMIN);

        Set<Role> roles = new HashSet<>();
        roles.add(role1);
        roles.add(role2);

        user.setRoles(roles);

        assertEquals(2, user.getRoles().size());
        assertTrue(user.getRoles().contains(role1));
        assertTrue(user.getRoles().contains(role2));
    }
}
