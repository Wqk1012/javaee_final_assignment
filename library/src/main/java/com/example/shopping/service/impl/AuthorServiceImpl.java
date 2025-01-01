package com.example.shopping.service.impl;

import com.example.shopping.mapper.AuthorMapper;
import com.example.shopping.pojo.Author;
import com.example.shopping.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AuthorServiceImpl implements AuthorService {
    @Autowired
    private AuthorMapper authorMapper;

    @Override
    public Author getAuthorById(Integer id) {
        return authorMapper.getAuthorById(id);
    }

    @Override
    public List<Author> getAllAuthors() {
        return authorMapper.getAllAuthors();
    }

    @Override
    public Author getAuthorByName(String name) {
        return authorMapper.getAuthorByName(name);
    }

    @Override
    public Integer insertAuthor(Author author) {
        return authorMapper.insertAuthor(author);
    }

    @Override
    public Integer deleteAuthorById(Integer id) {
        return authorMapper.deleteAuthorById(id);
    }

    @Override
    public Integer updateAuthor(Author author) {
        return authorMapper.updateAuthor(author);
    }
}
