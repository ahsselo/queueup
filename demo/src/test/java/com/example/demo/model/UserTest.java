package com.example.demo.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;


class UserTest {
    private User user;

    @BeforeEach
    public void setUp() {
        user = new User(1, "testuser", "test@example.com", true);
    }

    @Test
    public void testConstructor() {
        assertEquals(1, user.getUserID());
        assertEquals("testuser", user.getUsername());
        assertEquals("test@example.com", user.getEmail());
        assertTrue(user.isHasPremium());
    }

    @Test
    public void testSetUserID() {
        user.setUserID(2);
        assertEquals(2, user.getUserID());
    }

    @Test
    public void testSetUsername() {
        user.setUsername("newuser");
        assertEquals("newuser", user.getUsername());
    }

    @Test
    public void testSetEmail() {
        user.setEmail("new@example.com");
        assertEquals("new@example.com", user.getEmail());
    }

    @Test
    public void testSetHasPremium() {
        user.setHasPremium(false);
        assertFalse(user.isHasPremium());
    }


}

