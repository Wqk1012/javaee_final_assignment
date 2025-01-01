package com.example.shopping.service;

import com.example.shopping.pojo.Book;
import com.example.shopping.pojo.BorrowRecord;
import com.example.shopping.pojo.User;
import com.example.shopping.pojo.UserBorrowedBooksCount;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface BorrowRecordService {
    // 修改借阅记录
    Integer updateBorrowRecord(BorrowRecord borrowRecord);

    // 删除借阅记录
    Integer deleteBorrowRecord(Integer borrowRecordId);

    // 增加借阅记录
    Integer addBorrowRecord( Map<String, Object> params);

    // 归还图书
    Integer returnBorrowedBook(Integer borrowRecordId);

    // 查询某个用户借阅的所有图书(多表)
    List<Book> findBorrowedBooksByUserId(Integer userId);

    // 多表查询：查询借阅了指定图书的用户(多表)
    List<User> getUsersByBorrowedBook(Integer bookId);

    // 查询所有借阅的图书及其归还日期
    List<BorrowRecord> findAllBorrowedBooks();

    // 查询某个作者的所有图书借阅记录（多表）
    List<BorrowRecord> findBooksBorrowedByAuthor(Integer authorId);

    // 查询每个用户借阅图书数量(多表)
    List<UserBorrowedBooksCount> findUserBorrowedBooksCount();

    // 查询单个用户借阅图书数量(多表)
    UserBorrowedBooksCount findUserBorrowedBooksCountByUserId(Integer userId);

    //通过ID查询借阅记录
    BorrowRecord findBorrowRecordById(Integer borrowRecordId);
}
