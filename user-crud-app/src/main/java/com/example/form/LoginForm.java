package com.example.form;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import javax.servlet.http.HttpServletRequest;

public class LoginForm extends ActionForm {
    private String username;
    private String password;
    private String rememberMe;
    
    // Getters and Setters
    public String getUsername() {
        return username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getRememberMe() {
        return rememberMe;
    }
    
    public void setRememberMe(String rememberMe) {
        this.rememberMe = rememberMe;
    }
    
    @Override
    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
        ActionErrors errors = new ActionErrors();
        
        // Validate username
        if (username == null || username.trim().length() == 0) {
            errors.add("username", new ActionMessage("error.login.username.required"));
        }
        
        // Validate password
        if (password == null || password.trim().length() == 0) {
            errors.add("password", new ActionMessage("error.login.password.required"));
        }
        
        return errors;
    }
    
    @Override
    public void reset(ActionMapping mapping, HttpServletRequest request) {
        this.username = null;
        this.password = null;
        this.rememberMe = null;
    }
}
