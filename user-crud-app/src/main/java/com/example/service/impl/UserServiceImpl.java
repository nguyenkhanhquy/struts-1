package com.example.service.impl;

import com.example.dao.UserDAO;
import com.example.model.User;
import com.example.service.UserService;

import java.util.Date;
import java.util.List;

public class UserServiceImpl implements UserService {
    private UserDAO userDAO;
    
    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }
    
    @Override
    public void saveUser(User user) throws Exception {
        // Kiểm tra username đã tồn tại chưa
        if (isUsernameExists(user.getUsername())) {
            throw new Exception("Tên đăng nhập đã tồn tại: " + user.getUsername());
        }
        
        // Kiểm tra email đã tồn tại chưa
        if (isEmailExists(user.getEmail())) {
            throw new Exception("Email đã tồn tại: " + user.getEmail());
        }
        
        // Set ngày tạo nếu chưa có
        if (user.getCreatedDate() == null) {
            user.setCreatedDate(new Date());
        }
        
        userDAO.save(user);
    }
    
    @Override
    public void updateUser(User user) throws Exception {
        // Kiểm tra user có tồn tại không
        User existingUser = userDAO.findById(user.getId());
        if (existingUser == null) {
            throw new Exception("Không tìm thấy user với ID: " + user.getId());
        }
        
        // Kiểm tra username có bị trùng với user khác không
        User userWithSameUsername = userDAO.findByUsername(user.getUsername());
        if (userWithSameUsername != null && !userWithSameUsername.getId().equals(user.getId())) {
            throw new Exception("Tên đăng nhập đã tồn tại: " + user.getUsername());
        }
        
        // Kiểm tra email có bị trùng với user khác không
        User userWithSameEmail = userDAO.findByEmail(user.getEmail());
        if (userWithSameEmail != null && !userWithSameEmail.getId().equals(user.getId())) {
            throw new Exception("Email đã tồn tại: " + user.getEmail());
        }
        
        userDAO.update(user);
    }
    
    @Override
    public void deleteUser(Long id) throws Exception {
        User existingUser = userDAO.findById(id);
        if (existingUser == null) {
            throw new Exception("Không tìm thấy user với ID: " + id);
        }
        
        userDAO.delete(id);
    }
    
    @Override
    public User getUserById(Long id) {
        return userDAO.findById(id);
    }
    
    @Override
    public User getUserByUsername(String username) {
        return userDAO.findByUsername(username);
    }
    
    @Override
    public User getUserByEmail(String email) {
        return userDAO.findByEmail(email);
    }
    
    @Override
    public List<User> getAllUsers() {
        return userDAO.findAll();
    }
    
    @Override
    public boolean isUsernameExists(String username) {
        return userDAO.findByUsername(username) != null;
    }
    
    @Override
    public boolean isEmailExists(String email) {
        return userDAO.findByEmail(email) != null;
    }
    
    @Override
    public boolean validateUser(String username, String password) {
        User user = userDAO.findByUsername(username);
        return user != null && user.getPassword().equals(password);
    }
}
