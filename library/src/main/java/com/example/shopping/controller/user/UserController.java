package com.example.shopping.controller.user;

import com.example.shopping.mapper.UserMapper;
import com.example.shopping.pojo.Result;
import com.example.shopping.pojo.User;
import com.example.shopping.service.UserService;
import com.example.shopping.util.PasswordUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    @Operation(summary = "注册")
    @PostMapping("/register")
    public Result register(@Validated @RequestBody User user,String emailCode){
        String code = redisTemplate.opsForValue().get("emailcode");
        if (!code.equals(emailCode)){
            return Result.error("邮箱验证码不正确");
        }
        User findUser = userService.findUserByUserName(user.getUsername());
        if (findUser != null){
            return Result.error("用户名已存在");
        }
        String password = user.getPassword();
        user.setPassword(PasswordUtil.encryptPassword(password));
        Integer num = userService.addUser(user);
        if (num<1){
            return Result.error("服务器错误，联系管理员");
        }
        return Result.success("注册成功");
    }

}
