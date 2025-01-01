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


    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    @Test
    public void testStringRedisTemplate() {

//        User user = new User();
//        user.setUsername("wqk");
//        user.setPassword("123456");
//        user.setAvatarPath("/test");
//        user.setEmail("1@qq.com");
//        user.setRole("USER");
//        userMapper.insertUser(user);
//        User user1 = new User();
//        user1.setUsername("wqk001");
//        user1.setId(7);
//        System.out.println(userMapper.updateUser(user1));
        System.out.println(userMapper.getAllUsers());
        System.out.println(userMapper.getUserById(6));
        System.out.println(userMapper.getUserByUsername("wangwu"));

    }

    @Autowired
    private UserMapper userMapper;
    @Test
    public void test(){
        System.out.println(userMapper.getUserById(1));
        System.out.println(userMapper.getUserByUsername("lisi"));
    }

    @Autowired
    private BookMapper bookMapper;
    @Test
    public void testbook(){
        System.out.println(bookMapper.getBookByTitle("活着"));
    }

    @Autowired
    private BorrowRecordMapper borrowRecordMapper;
    @Test
    public void testBorrow(){
        System.out.println(borrowRecordMapper.findBooksBorrowedByAuthor(1));

    }

    @Autowired
    private ReservationMapper reservationMapper;
    @Test
    public void testReservation(){
//        System.out.println(reservationMapper.findReservationsByBookId(8));
//        System.out.println(reservationMapper.findAllReservations());
        Reservation reservation = new Reservation();
        reservation.setUserId(4);
        reservation.setBookId(4);
        reservationMapper.addReservation(reservation);
    }

    @Autowired
    private RedisTemplate<String, String> redisTemplate1;
    @Test
    public void getFromRedis() {
        // 从 Redis 获取值
    System.out.println(redisTemplate1.opsForValue().get("captcha"));
}

    @Autowired
    CodeController controller;
    @Autowired
    SendMail sendMail;
    @Test
    public void sjs(){
                User user = new User();
                user.setId(56);
        user.setUsername("wqk");
        user.setPassword("123456");
        user.setAvatarPath("/test");
        user.setEmail("1@qq.com");
        user.setRole("USER");
        String jwtToken = JwtUtil.generateToken(user);
        System.out.println(JwtUtil.getIdFromToken(jwtToken));
        System.out.println(JwtUtil.getRoleFromToken(jwtToken));
        System.out.println(JwtUtil.getUsernameFromToken(jwtToken));
        System.out.println(jwtToken);
    }

    @Autowired
    CategoryMapper categoryMapper;
    @Test
    public void testCategory(){
        categoryMapper.deleteCategoryById(6);
    }

    @Autowired
    AuthorMapper authorMapper;
    @Test
    public void testAuthor(){
        System.out.println(authorMapper.getAuthorById(2));
        System.out.println(authorMapper.getAuthorByName("金庸"));
    }
}
