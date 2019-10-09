package com.karim.facebookapplication.service;

import com.karim.facebookapplication.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    public List<User> findAll();
    public User findByUsername(String username);
    public User findByUsernameAndPassword(String username , String password);
    public User findById(Long id);
    public User save(User user);
    public User update (User user , long id);
    public void deleteById(long id);
    public void delete(User user);
}
