package com.example.shopping.service.impl;

import com.example.shopping.mapper.BorrowRecordMapper;
import com.example.shopping.pojo.Book;
import com.example.shopping.pojo.BorrowRecord;
import com.example.shopping.pojo.User;
import com.example.shopping.pojo.UserBorrowedBooksCount;
import com.example.shopping.service.BorrowRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class BorrowRecordServiceImpl implements BorrowRecordService {
    @Autowired
    private BorrowRecordMapper borrowRecordMapper;


    // 修改借阅记录
    @Override
    public Integer updateBorrowRecord(BorrowRecord borrowRecord) {
        return borrowRecordMapper.updateBorrowRecord(borrowRecord);
    }

    // 删除借阅记录
    @Override
    public Integer deleteBorrowRecord(Integer borrowRecordId) {
        return borrowRecordMapper.deleteBorrowRecord(borrowRecordId);
    }

    // 增加借阅记录
    @Override
    public Integer addBorrowRecord( Map<String, Object> params) {
        return borrowRecordMapper.addBorrowRecord(params);
    }

    // 归还图书
    @Override
    public Integer returnBorrowedBook(Integer borrowRecordId) {
        return borrowRecordMapper.returnBorrowedBook(borrowRecordId);
    }

    // 查询某个用户借阅的所有图书(多表)
    @Override
    public List<Book> findBorrowedBooksByUserId(Integer userId) {
        return borrowRecordMapper.findBorrowedBooksByUserId(userId);
    }

    // 多表查询：查询借阅了指定图书的用户(多表)
    @Override
    public List<User> getUsersByBorrowedBook(Integer bookId) {
        return borrowRecordMapper.getUsersByBorrowedBook(bookId);
    }

    // 查询所有借阅的图书及其归还日期
    @Override
    public List<BorrowRecord> findAllBorrowedBooks() {
        return borrowRecordMapper.findAllBorrowedBooks();
    }

    // 查询某个作者的所有图书借阅记录（多表）
    @Override
    public List<BorrowRecord> findBooksBorrowedByAuthor(Integer authorId) {
        return borrowRecordMapper.findBooksBorrowedByAuthor(authorId);
    }

    // 查询每个用户借阅图书数量(多表)
    @Override
    public List<UserBorrowedBooksCount> findUserBorrowedBooksCount() {
        return borrowRecordMapper.findUserBorrowedBooksCount();
    }

    // 查询单个用户借阅图书数量(多表)
    @Override
    public UserBorrowedBooksCount findUserBorrowedBooksCountByUserId(Integer userId) {
        return borrowRecordMapper.findUserBorrowedBooksCountByUserId(userId);
    }

    @Override
    public BorrowRecord findBorrowRecordById(Integer borrowRecordId) {
        return borrowRecordMapper.findBorrowRecordById(borrowRecordId);
    }
}
