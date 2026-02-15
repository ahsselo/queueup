package com.example.demo.model;

public class User {
    private int userID;
    private String username;
    private String email;
    private boolean hasPremium;

    // Constructor
    public User(int userID, String username, String email, boolean hasPremium) {
        this.userID = userID;
        this.username = username;
        this.email = email;
        this.hasPremium = hasPremium;
    }

    // Getters and Setters
    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isHasPremium() {
        return hasPremium;
    }

    public void setHasPremium(boolean hasPremium) {
        this.hasPremium = hasPremium;
    }

    @Override
    public String toString() {
        return "User{" +
                "userID=" + userID +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", hasPremium=" + hasPremium +
                '}';
    }

}