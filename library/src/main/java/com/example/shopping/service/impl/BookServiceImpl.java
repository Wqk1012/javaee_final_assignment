package com.example.shopping.service.impl;

import com.example.shopping.mapper.BookMapper;
import com.example.shopping.pojo.Book;
import com.example.shopping.pojo.BookDTO;
import com.example.shopping.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookMapper bookMapper;
    @Override
    public List<Book> findAllBooksWithAuthors() {
        return bookMapper.findAllBooksWithAuthors();
    }

    @Override
    public Book findBookById(Integer id) {
        return bookMapper.findBookById(id);
    }

    @Override
    public List<Book> findBooksByCategoryId(Integer categoryId) {
        return bookMapper.findBooksByCategoryId(categoryId);
    }

    @Override
    public List<Book> findBooksByAuthorId(Integer authorId) {
        return bookMapper.findBooksByAuthorId(authorId);
    }

    @Override
    public Integer addBook(BookDTO bookDTO) {
        return bookMapper.addBook(bookDTO);
    }

    @Override
    public Integer updateBook(BookDTO bookDTO) {
        return bookMapper.updateBook(bookDTO);
    }

    @Override
    public Integer deleteBook(Integer id) {
        return bookMapper.deleteBook(id);
    }
}
