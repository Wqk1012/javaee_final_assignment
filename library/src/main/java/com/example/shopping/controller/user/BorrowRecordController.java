package com.example.shopping.controller.user;

import com.example.shopping.pojo.Book;
import com.example.shopping.pojo.BorrowRecord;
import com.example.shopping.pojo.Result;
import com.example.shopping.pojo.UserBorrowedBooksCount;
import com.example.shopping.service.BookService;
import com.example.shopping.service.BorrowRecordService;
import com.example.shopping.util.JwtUtil;
import com.example.shopping.util.ThreadLocalUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user/borrowRecord")
@Tag(name = "借阅记录查询", description = "提供借阅记录查询相关操作的API")
public class BorrowRecordController {
    @Autowired
    private BorrowRecordService borrowRecordService;
    @Autowired
    private BookService bookService;

    @PostMapping("/add")
    @Operation(summary = "增加借阅记录")
    public Result add(Integer bookId){
        //获取用户id
        String token = ThreadLocalUtil.get();
        Integer userId = JwtUtil.getIdFromToken(token);
        //判断图书是否存在
        Book findBook = bookService.findBookById(bookId);
        if (findBook == null){
            return Result.error("图书不存在");
        }
        Map<String, Object> params = new HashMap<>();
        params.put("bookId", bookId);
        params.put("userId", userId);
        params.put("id", null);
        Integer count = borrowRecordService.addBorrowRecord(params);
        if (count<1){
            return Result.error("借阅失败");
        }
        return Result.success("借阅"+findBook.getTitle()+"成功");
    }

    @PostMapping("/return")
    @Operation(summary = "归还图书")
    public Result returnBook(Integer borrowRecordId){
        BorrowRecord borrowRecordById = borrowRecordService.findBorrowRecordById(borrowRecordId);
        if (borrowRecordById == null){
            return Result.error("没有这条借阅记录");
        }
        Integer count = borrowRecordService.returnBorrowedBook(borrowRecordId);
        if (count<1){
            return Result.error("归还失败");
        }
        return Result.success("归还成功");
    }

    @GetMapping("/findBorrowedBooksByUserId")
    @Operation(summary = "查询当前用户借阅的所有图书")
    public Result<List<Book>> findBorrowedBooksByUserId(){
        //获取用户id
        String token = ThreadLocalUtil.get();
        Integer userId = JwtUtil.getIdFromToken(token);
        List<Book> borrowedBooksByUserId = borrowRecordService.findBorrowedBooksByUserId(userId);
        if (borrowedBooksByUserId.isEmpty()){
            Result.error("当前用户没有借阅记录");
        }
        return Result.success(borrowedBooksByUserId);
    }

    @Operation(summary = "查询当前用户借阅图书数量")
    @GetMapping("/findUserBorrowedBooksCountByUserId")
    public Result<UserBorrowedBooksCount> findUserBorrowedBooksCountByUserId(){
        //获取用户id
        String token = ThreadLocalUtil.get();
        Integer userId = JwtUtil.getIdFromToken(token);
        UserBorrowedBooksCount user = borrowRecordService.findUserBorrowedBooksCountByUserId(userId);
        if (user == null){
            return Result.error("当前用户没有借阅记录");
        }
        return Result.success(user);
    }
}
