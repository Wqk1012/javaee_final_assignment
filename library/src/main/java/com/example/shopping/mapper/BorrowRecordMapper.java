package com.example.shopping.mapper;

import com.example.shopping.pojo.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface BorrowRecordMapper {
    //修改借阅记录
    Integer updateBorrowRecord(BorrowRecord borrowRecord);
    //删除借阅记录
    Integer deleteBorrowRecord(@Param("borrowRecordId") Integer borrowRecordId);
    //增加借阅记录
    Integer addBorrowRecord(@Param("params") Map<String, Object> params);
    //归还图书
    Integer returnBorrowedBook(@Param("borrowRecordId") Integer borrowRecordId);
    // 查询某个用户借阅的所有图书(多表)
    List<Book> findBorrowedBooksByUserId(@Param("userId") Integer userId);
    // 多表查询：查询借阅了指定图书的用户(多表)
    List<User> getUsersByBorrowedBook(@Param("bookId") Integer bookId);
    List<BorrowRecord> findAllBorrowedBooks();//查询所有借阅的图书及其归还日期

    List<BorrowRecord> findBooksBorrowedByAuthor(Integer authorId);//查询某个作者的所有图书借阅记录（多表）

    List<UserBorrowedBooksCount> findUserBorrowedBooksCount();//查询每个用户借阅图书数量(多表)
    UserBorrowedBooksCount findUserBorrowedBooksCountByUserId(@Param("userId") Integer userId);//查询单个个用户借阅图书数量(多表)

    BorrowRecord findBorrowRecordById(Integer borrowRecordId);
}
