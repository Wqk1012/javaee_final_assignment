package com.example.shopping.mapper;

import com.example.shopping.pojo.User;
import com.example.shopping.pojo.Book;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface UserMapper {

    // 查询所有用户
    List<User> getAllUsers();

    // 根据ID查询用户
    User getUserById(@Param("id") Integer id);

    // 根据用户名查询用户
    User getUserByUsername(@Param("username") String username);

    // 新增用户
    Integer insertUser(User user);

    // 更新用户信息
    Integer updateUser(User user);

    // 根据ID删除用户
    Integer deleteUserById(@Param("id") Integer id);


}
