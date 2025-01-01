package com.example.shopping;

import com.example.shopping.controller.user.CodeController;
import com.example.shopping.mapper.*;
import com.example.shopping.pojo.*;
import com.example.shopping.util.JwtUtil;
import com.example.shopping.util.PasswordUtil;
import com.example.shopping.util.SendMail;
import jakarta.servlet.http.HttpServletRequestWrapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class ShoppingApplicationTests {

}
