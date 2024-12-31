package com.example.shopping.service;

import com.example.shopping.pojo.User;
import org.springframework.stereotype.Service;


public interface UserService {
    User findUserByUserName(String username);
    Integer addUser(User user);
}
