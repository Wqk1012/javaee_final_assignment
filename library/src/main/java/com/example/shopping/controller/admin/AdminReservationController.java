package com.example.shopping.controller.admin;

import com.example.shopping.pojo.Book;
import com.example.shopping.pojo.Reservation;
import com.example.shopping.pojo.Result;
import com.example.shopping.service.BookService;
import com.example.shopping.service.ReservationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin/reservation")
@Tag(name = "预约记录管理", description = "提供预约记录管理相关操作的API")
public class AdminReservationController {
    @Autowired
    private ReservationService reservationService;
    @Autowired
    private BookService bookService;

    @GetMapping("/findByBookId")
    @Operation(summary = "查询指定图书的预约记录")
    public Result<List<Reservation>> findByBookId(Integer bookId){
        //判断书籍是否存在
        Book bookById = bookService.findBookById(bookId);
        if (bookById == null){
            return Result.error("书籍不存在");
        }
        List<Reservation> reservationsByBookId = reservationService.findReservationsByBookId(bookId);
        if (reservationsByBookId.isEmpty()){
            return Result.error(bookById.getTitle()+"：没有借阅记录");
        }
        return Result.success(reservationsByBookId);
    }

    @GetMapping("/findAll")
    @Operation(summary = "查询所有图书的预约记录")
    public Result<List<Reservation>> findAll(){
        List<Reservation> allReservations = reservationService.findAllReservations();
        return Result.success(allReservations);
    }
}
