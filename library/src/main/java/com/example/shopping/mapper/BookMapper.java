package com.example.shopping.mapper;

import com.example.shopping.pojo.Book;
import com.example.shopping.pojo.BookDTO;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface BookMapper {

    List<Book> findAllBooksWithAuthors();//查询所有图书及其对应的作者信息（多表）
    Book findBookById(@Param("id") Integer id);//通过id查询图书详细信息（多表）

    //查询某一分类下的所有图书(多表)

    List<Book> findBooksByCategoryId(@Param("categoryId") Integer categoryId);

    // 根据 authorId 查询所有属于该作者的图书(多表)
    List<Book> findBooksByAuthorId(@Param("authorId") Integer authorId);

    Integer addBook(BookDTO bookDTO);//新增图书

    Integer updateBook(BookDTO bookDTO);//修改图书

    Integer deleteBook(Integer id);//删除图书

}
