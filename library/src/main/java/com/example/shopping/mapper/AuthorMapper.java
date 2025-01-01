package com.example.shopping.mapper;

import com.example.shopping.pojo.Author;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AuthorMapper {
    int insertAuthor(Author author); // 插入作者

    int deleteAuthorById(int id); // 删除作者

    int updateAuthor(Author author); // 更新作者

    Author getAuthorById(int id); // 根据 ID 查询作者

    List<Author> getAllAuthors(); // 查询所有作者

    Author getAuthorByName(String name); // 根据名字查询作者
}
