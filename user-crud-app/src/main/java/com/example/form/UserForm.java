package com.example.form;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import javax.servlet.http.HttpServletRequest;

public class UserForm extends ActionForm {
    private String id;
    private String username;
    private String email;
    private String fullName;
    private String password;
    private String confirmPassword;
    
    // Getters and Setters
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
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
    
    public String getFullName() {
        return fullName;
    }
    
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getConfirmPassword() {
        return confirmPassword;
    }
    
    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
    
    @Override
    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
        ActionErrors errors = new ActionErrors();
        
        // Validate username
        if (username == null || username.trim().length() == 0) {
            errors.add("username", new ActionMessage("error.username.required"));
        } else if (username.trim().length() < 3) {
            errors.add("username", new ActionMessage("error.username.minlength"));
        }
        
        // Validate email
        if (email == null || email.trim().length() == 0) {
            errors.add("email", new ActionMessage("error.email.required"));
        } else if (!isValidEmail(email)) {
            errors.add("email", new ActionMessage("error.email.invalid"));
        }
        
        // Validate password (chỉ khi tạo mới hoặc thay đổi password)
        if ((id == null || id.trim().length() == 0) && 
            (password == null || password.trim().length() == 0)) {
            errors.add("password", new ActionMessage("error.password.required"));
        } else if (password != null && password.trim().length() > 0 && password.trim().length() < 6) {
            errors.add("password", new ActionMessage("error.password.minlength"));
        }
        
        // Validate confirm password
        if (password != null && password.trim().length() > 0) {
            if (confirmPassword == null || !password.equals(confirmPassword)) {
                errors.add("confirmPassword", new ActionMessage("error.password.mismatch"));
            }
        }
        
        return errors;
    }
    
    private boolean isValidEmail(String email) {
        return email.matches("^[A-Za-z0-9+_.-]+@(.+)$");
    }
    
    @Override
    public void reset(ActionMapping mapping, HttpServletRequest request) {
        this.id = null;
        this.username = null;
        this.email = null;
        this.fullName = null;
        this.password = null;
        this.confirmPassword = null;
    }
}
