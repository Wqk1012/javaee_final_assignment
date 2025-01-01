package com.example.shopping.controller.user;

import com.example.shopping.mapper.CategoryMapper;
import com.example.shopping.pojo.Author;
import com.example.shopping.pojo.Book;
import com.example.shopping.pojo.Category;
import com.example.shopping.pojo.Result;
import com.example.shopping.service.AuthorService;
import com.example.shopping.service.BookService;
import com.example.shopping.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user/book")
public class BookController {
    @Autowired
    private BookService bookService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private AuthorService authorService;


    @Operation(summary = "获取全部图书")
    @GetMapping("/allBook")
    public Result<List<Book>> findAllBooks(){
        return Result.success(bookService.findAllBooksWithAuthors());
    }

    @Operation(summary = "通过id查询图书详细信息")
    @GetMapping("/findBookById")
    public Result<Book> findBookById(Integer bookId){
        Book findBook = bookService.findBookById(bookId);
        if (findBook == null){
            return Result.error("图书不存在");
        }
        return Result.success(findBook);
    }

    @Operation(summary = "通过分类查询图书详细信息列表")
    @GetMapping("/findBookByCategoryId")
    public Result<List<Book>> findBooksByCategoryId(Integer categoryId){
        Category category = categoryService.getCategoryById(categoryId);
        if (category == null){
            return Result.error("此分类不存在");
        }
        List<Book> booksByCategoryId = bookService.findBooksByCategoryId(categoryId);
        if (booksByCategoryId .isEmpty()){
            return Result.error("此分类下暂无图书");
        }
        return Result.success(booksByCategoryId);
    }

    @Operation(summary = "通过作者查询图书详细信息列表")
    @GetMapping("/findBooksByAuthorId")
    public Result<List<Book>> findBooksByAuthorId(Integer authorId){
        Author author = authorService.getAuthorById(authorId);
        if (author == null){
            return Result.error("此作者不存在");
        }
        List<Book> books = bookService.findBooksByAuthorId(authorId);
        if (books.isEmpty()){
            return Result.error("此作者暂无图书");
        }
        return Result.success(books);
    }

}
