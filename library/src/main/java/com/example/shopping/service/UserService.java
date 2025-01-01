package com.example.shopping.service;


import com.example.shopping.pojo.User;
import org.springframework.stereotype.Service;

import java.util.List;


public interface UserService {
    User findUserByUserName(String username);
    Integer addUser(User user);
    Integer deleteUser(Integer userId);
    Integer updateUser(User user);

    User findUserById(Integer userId);

    List<User> findAllUsers();
}
