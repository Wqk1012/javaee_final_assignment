package com.example.shopping.service;

import com.example.shopping.pojo.Author;

import java.util.List;

public interface AuthorService {
    Author getAuthorById(Integer id); // 根据 ID 查询作者

    List<Author> getAllAuthors(); // 查询所有作者

    Author getAuthorByName(String name); // 根据名字查询作者
    Integer insertAuthor(Author author); // 插入作者

    Integer deleteAuthorById(Integer id); // 删除作者

    Integer updateAuthor(Author author); // 更新作者
}
