package com.example.shopping.service.impl;


import com.example.shopping.mapper.UserMapper;
import com.example.shopping.pojo.User;
import com.example.shopping.service.UserService;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User findUserByUserName(String username) {
        return userMapper.getUserByUsername(username);
    }

    @Override
    public Integer addUser(User user) {
        return userMapper.insertUser(user);
    }

    @Override
    public Integer deleteUser(Integer userId) {
        return userMapper.deleteUserById(userId);
    }

    @Override
    public Integer updateUser(User user) {
        return userMapper.updateUser(user);
    }

    @Override
    public User findUserById(Integer userId) {
        return userMapper.getUserById(userId);
    }


}
