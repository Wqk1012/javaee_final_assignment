package com.example.shopping.controller.admin;

import com.example.shopping.pojo.*;
import com.example.shopping.service.AuthorService;
import com.example.shopping.service.BookService;
import com.example.shopping.service.BorrowRecordService;
import com.example.shopping.util.JwtUtil;
import com.example.shopping.util.ThreadLocalUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/borrowrecord")
@Tag(name = "借阅记录管理", description = "提供借阅记录管理相关操作的API")
public class AdminBorrowRecordController {
    @Autowired
    private BorrowRecordService borrowRecordService;
    @Autowired
    private AuthorService authorService;
    @Autowired
    private BookService bookService;

    @Operation(summary = "修改借阅记录")
    @PutMapping("/update")
    public Result updateBorrowRecord(@RequestBody @Validated BorrowRecord borrowRecord) {
        //判断借阅记录是否存在
        BorrowRecord borrowRecordById = borrowRecordService.findBorrowRecordById(borrowRecord.getId());
        if (borrowRecordById == null){
            return Result.error("没有这条记录");
        }
        //判断图书是否存在
        Book findBook = bookService.findBookById(borrowRecord.getBookId());
        if (findBook == null){
            return Result.error("图书不存在");
        }
        Integer count = borrowRecordService.updateBorrowRecord(borrowRecord);
        if (count < 1) {
            return Result.error("更新借阅记录失败");
        }
        return Result.success("更新借阅记录成功");
    }

    @Operation(summary = "删除借阅记录")
    @DeleteMapping("/delete/{borrowRecordId}")
    public Result deleteBorrowRecord(@PathVariable Integer borrowRecordId) {
        //判断借阅记录是否存在
        BorrowRecord borrowRecordById = borrowRecordService.findBorrowRecordById(borrowRecordId);
        if (borrowRecordById == null){
            return Result.error("没有这条记录");
        }
        Integer count = borrowRecordService.deleteBorrowRecord(borrowRecordId);
        if (count < 1) {
            return Result.error("删除借阅记录失败");
        }
        return Result.success("删除借阅记录成功");
    }

    @Operation(summary = "查询借阅了指定图书的用户")
    @GetMapping("/getUsersByBorrowedBook")
    public Result<List<User>> getUsersByBorrowedBook(Integer bookId){
        //判断图书是否存在
        Book findBook = bookService.findBookById(bookId);
        if (findBook == null){
            return Result.error("图书不存在");
        }
        //存在图书就开始查询
        List<User> usersByBorrowedBook = borrowRecordService.getUsersByBorrowedBook(bookId);
        if (usersByBorrowedBook.isEmpty()){
            return Result.error("没有记录");
        }
        return Result.success(usersByBorrowedBook);
    }

    @Operation(summary = "查询所有借阅的图书及其归还日期")
    @GetMapping("/findAllBorrowedBooks")
    public Result<List<BorrowRecord>> findAllBorrowedBooks(){
        List<BorrowRecord> borrow = borrowRecordService.findAllBorrowedBooks();
        return Result.success(borrow);
    }

    @Operation(summary = "查询某个作者的所有图书借阅记录")
    @GetMapping("/findBooksBorrowedByAuthor")
    public Result<List<BorrowRecord>> findBooksBorrowedByAuthor(Integer authorId){
        //判断作者是否存在
        Author authorById = authorService.getAuthorById(authorId);
        if (authorById == null){
            return Result.error("作家不存在");
        }
        //作家存在就查询
        List<BorrowRecord> borrowed = borrowRecordService.findBooksBorrowedByAuthor(authorId);
        if (borrowed.isEmpty()){
            return Result.error(authorById.getName()+"没有借阅记录");
        }
        return Result.success(borrowed);
    }

    @Operation(summary = "查询每个用户借阅图书数量")
    @GetMapping("/findUserBorrowedBooksCount")
    public Result<List<UserBorrowedBooksCount>> findUserBorrowedBooksCount(){
        List<UserBorrowedBooksCount> userBorrowedBooksCount = borrowRecordService.findUserBorrowedBooksCount();
        return Result.success(userBorrowedBooksCount);
    }
}
