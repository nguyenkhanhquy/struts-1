package com.example.dao.impl;

import com.example.dao.UserDAO;
import com.example.model.User;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.util.List;

public class UserDAOImpl extends HibernateDaoSupport implements UserDAO {
    
    @Override
    public void save(User user) {
        getHibernateTemplate().save(user);
    }
    
    @Override
    public void update(User user) {
        getHibernateTemplate().update(user);
    }
    
    @Override
    public void delete(Long id) {
        User user = findById(id);
        if (user != null) {
            getHibernateTemplate().delete(user);
        }
    }
    
    @Override
    public User findById(Long id) {
        return (User) getHibernateTemplate().get(User.class, id);
    }
    
    @Override
    public User findByUsername(String username) {
        List<User> users = getHibernateTemplate().find(
            "FROM User WHERE username = ?", username);
        return users.isEmpty() ? null : users.get(0);
    }
    
    @Override
    public User findByEmail(String email) {
        List<User> users = getHibernateTemplate().find(
            "FROM User WHERE email = ?", email);
        return users.isEmpty() ? null : users.get(0);
    }
    
    @Override
    @SuppressWarnings("unchecked")
    public List<User> findAll() {
        return getHibernateTemplate().find(
            "FROM User ORDER BY createdDate DESC");
    }
    
    @Override
    public Long countUsers() {
        List<Long> result = getHibernateTemplate().find("SELECT COUNT(*) FROM User");
        return result.get(0);
    }
    
    @Override
    @SuppressWarnings("unchecked")
    public List<User> findUsersWithPaging(int pageNumber, int pageSize) {
        return getHibernateTemplate().executeFind(session -> {
            return session.createQuery("FROM User ORDER BY createdDate DESC")
                          .setFirstResult((pageNumber - 1) * pageSize)
                          .setMaxResults(pageSize)
                          .list();
        });
    }
}
