package com.example.demo.model; /**
 * SessionURL Class
 * 
 * Purpose: Manages a set of active session URLs, allowing generation, checking, and deactivation of URLs.
 * 
 * Attributes:
 * - activeURLs (Set<String>): A set that holds all the currently active session URLs.
 * 
 * Methods:
 * - SessionURL(): Constructor that initializes an empty set of active URLs.
 * - generateURL(): Generates a new unique session URL, adds it to the active URLs set, and returns the URL.
 * - getActiveURLs(): Returns the set of all active session URLs.
 * - isURLActive(String url): Checks if a given URL is currently active.
 * - deactivateURL(String url): Removes a given URL from the active URLs set, effectively deactivating it.
 */

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class SessionURL {
    private Set<String> activeURLs;

    public SessionURL() {
        this.activeURLs = new HashSet<>();
    }

    public String generateURL() {
        String newUrl = "https://session/" + UUID.randomUUID().toString();
        activeURLs.add(newUrl);
        return newUrl;
    }

    public Set<String> getActiveURLs() {
        return activeURLs;
    }

    public boolean isURLActive(String url) {
        return activeURLs.contains(url);
    }

    public boolean deactivateURL(String url) {
        return activeURLs.remove(url);
    }
}
