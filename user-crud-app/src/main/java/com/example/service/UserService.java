package com.example.service;

import com.example.model.User;
import java.util.List;

public interface UserService {
    /**
     * Lưu user mới
     */
    void saveUser(User user) throws Exception;
    
    /**
     * Cập nhật user
     */
    void updateUser(User user) throws Exception;
    
    /**
     * Xóa user
     */
    void deleteUser(Long id) throws Exception;
    
    /**
     * Lấy user theo ID
     */
    User getUserById(Long id);
    
    /**
     * Lấy user theo username
     */
    User getUserByUsername(String username);
    
    /**
     * Lấy user theo email
     */
    User getUserByEmail(String email);
    
    /**
     * Lấy tất cả users
     */
    List<User> getAllUsers();
    
    /**
     * Kiểm tra username có tồn tại không
     */
    boolean isUsernameExists(String username);
    
    /**
     * Kiểm tra email có tồn tại không
     */
    boolean isEmailExists(String email);
    
    /**
     * Validate user login
     */
    boolean validateUser(String username, String password);
}
