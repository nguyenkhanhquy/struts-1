package com.example.model;

import java.io.Serializable;
import java.util.Date;

public class UserSession implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private Long userId;
    private String username;
    private String fullName;
    private String email;
    private Date loginTime;
    private boolean isLoggedIn;
    
    // Constructors
    public UserSession() {
        this.isLoggedIn = false;
    }
    
    public UserSession(User user) {
        this.userId = user.getId();
        this.username = user.getUsername();
        this.fullName = user.getFullName();
        this.email = user.getEmail();
        this.loginTime = new Date();
        this.isLoggedIn = true;
    }
    
    // Getters and Setters
    public Long getUserId() {
        return userId;
    }
    
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    
    public String getUsername() {
        return username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getFullName() {
        return fullName;
    }
    
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public Date getLoginTime() {
        return loginTime;
    }
    
    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }
    
    public boolean isLoggedIn() {
        return isLoggedIn;
    }
    
    public void setLoggedIn(boolean loggedIn) {
        isLoggedIn = loggedIn;
    }
    
    // Utility methods
    public void logout() {
        this.isLoggedIn = false;
        this.userId = null;
        this.username = null;
        this.fullName = null;
        this.email = null;
        this.loginTime = null;
    }
}
