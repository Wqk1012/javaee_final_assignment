package com.example.shopping.controller.user;

import com.example.shopping.pojo.Book;
import com.example.shopping.pojo.Reservation;
import com.example.shopping.pojo.Result;
import com.example.shopping.service.BookService;
import com.example.shopping.service.ReservationService;
import com.example.shopping.util.JwtUtil;
import com.example.shopping.util.ThreadLocalUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user/Reservation")
@Tag(name = "预约记录管理", description = "提供预约记录管理相关操作的API")
public class ReservationController {
    @Autowired
    private ReservationService reservationService;
    @Autowired
    private BookService bookService;


    @PutMapping("/add")
    @Operation(summary = "新增预约记录")
    public Result add(Integer bookId){
        //判断书籍是否存在
        Book bookById = bookService.findBookById(bookId);
        if (bookById == null){
            return Result.error("书籍不存在");
        }
        //从jwt中获取用户id保证用户存在
        String token = ThreadLocalUtil.get();
        Reservation reservation = new Reservation();
        reservation.setBookId(bookId);
        reservation.setUserId(JwtUtil.getIdFromToken(token));
        Integer count = reservationService.addReservation(reservation);
        if (count<1){
            return Result.error("预约失败");
        }
        return Result.success("预约书籍"+bookById.getTitle()+"成功");
    }

    @GetMapping("/getAll")
    @Operation(summary = "查询所有预约记录")
    public Result<List<Reservation>> getAll(){
        //通过JWT获取用户ID
        String token = ThreadLocalUtil.get();
        Integer userID = JwtUtil.getIdFromToken(token);
        List<Reservation> reservationsByUserId = reservationService.findReservationsByUserId(userID);
        if (reservationsByUserId.isEmpty()){
            return Result.error("您还没有预约记录");
        }
        return Result.success(reservationsByUserId);
    }
}
