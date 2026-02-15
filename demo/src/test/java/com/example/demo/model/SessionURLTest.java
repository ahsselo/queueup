package com.example.demo.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Set;

public class SessionURLTest {
    private SessionURL sessionURL;

    @BeforeEach
    public void setUp() {
        sessionURL = new SessionURL();
    }

    @Test
    public void testConstructor() {
        assertNotNull(sessionURL.getActiveURLs());
        assertTrue(sessionURL.getActiveURLs().isEmpty());
    }

    @Test
    public void testGenerateURL() {
        String url = sessionURL.generateURL();
        assertNotNull(url);
        assertTrue(url.startsWith("https://session/"));
        assertTrue(sessionURL.getActiveURLs().contains(url));
    }

    @Test
    public void testGetActiveURLs() {
        sessionURL.generateURL();
        Set<String> activeURLs = sessionURL.getActiveURLs();
        assertNotNull(activeURLs);
        assertFalse(activeURLs.isEmpty());
    }

    @Test
    public void testIsURLActive() {
        String url = sessionURL.generateURL();
        assertTrue(sessionURL.isURLActive(url));
        assertFalse(sessionURL.isURLActive("https://session/nonexistent"));
    }

    @Test
    public void testDeactivateURL() {
        String url = sessionURL.generateURL();
        assertTrue(sessionURL.isURLActive(url));
        sessionURL.deactivateURL(url);
        assertFalse(sessionURL.isURLActive(url));
        assertFalse(sessionURL.getActiveURLs().contains(url));
    }
}
