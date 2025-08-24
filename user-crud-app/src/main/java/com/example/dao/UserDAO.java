package com.example.dao;

import com.example.model.User;
import java.util.List;

public interface UserDAO {
    /**
     * Lưu user mới
     */
    void save(User user);
    
    /**
     * Cập nhật user
     */
    void update(User user);
    
    /**
     * Xóa user theo ID
     */
    void delete(Long id);
    
    /**
     * Tìm user theo ID
     */
    User findById(Long id);
    
    /**
     * Tìm user theo username
     */
    User findByUsername(String username);
    
    /**
     * Tìm user theo email
     */
    User findByEmail(String email);
    
    /**
     * Lấy tất cả users
     */
    List<User> findAll();
    
    /**
     * Đếm tổng số users
     */
    Long countUsers();
    
    /**
     * Tìm users với phân trang
     */
    List<User> findUsersWithPaging(int pageNumber, int pageSize);
}
