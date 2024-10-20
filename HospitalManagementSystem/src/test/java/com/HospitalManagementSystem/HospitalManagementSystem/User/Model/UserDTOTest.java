package com.HospitalManagementSystem.HospitalManagementSystem.User.Model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserDTOTest {

    @Test
    public void testUserDTOConstructorAndGetters() {
        // Arrange
        String id = "1";
        String username = "testUser";
        String email = "test@example.com";
        String link = "http://example.com";

        // Act
        UserDTO userDTO = new UserDTO(id, username, email, link);

        // Assert
        assertEquals(id, userDTO.getId());
        assertEquals(username, userDTO.getUsername());
        assertEquals(email, userDTO.getEmail());
        assertEquals(link, userDTO.getLink());
    }

    @Test
    public void testUserDTOSetters() {
        // Arrange
        UserDTO userDTO = new UserDTO();
        String id = "1";
        String username = "testUser";
        String email = "test@example.com";
        String link = "http://example.com";

        // Act
        userDTO.setId(id);
        userDTO.setUsername(username);
        userDTO.setEmail(email);
        userDTO.setLink(link);

        // Assert
        assertEquals(id, userDTO.getId());
        assertEquals(username, userDTO.getUsername());
        assertEquals(email, userDTO.getEmail());
        assertEquals(link, userDTO.getLink());
    }
}
