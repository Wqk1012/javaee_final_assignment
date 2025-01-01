package com.example.shopping.controller.admin;

import com.example.shopping.pojo.*;
import com.example.shopping.service.AuthorService;
import com.example.shopping.service.BookService;
import com.example.shopping.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/book")
@Tag(name = "图书管理", description = "提供图书管理相关操作的API")
public class AdminBookController {
    @Autowired
    private BookService bookService;
    @Autowired
    private AuthorService authorService;
    @Autowired
    private CategoryService categoryService;


    @Operation(summary = "新增书籍")
    @PostMapping("/add")
    public Result  addBook(@Validated @RequestBody BookDTO bookDTO){
        //获取分类和作者id
        Integer authorId = bookDTO.getAuthorId();
        Integer categoryId = bookDTO.getCategoryId();
        //查询分类和作者是否存在
        Author authorById = authorService.getAuthorById(authorId);
        Category categoryById = categoryService.getCategoryById(categoryId);
        if (authorById == null || categoryById == null){
            return Result.error("作家或者分类不存在");
        }
        BookDTO title = bookService.getBookByTitle(bookDTO.getTitle());
        if (title != null){
            return Result.error("书籍已存在");
        }
        Integer count = bookService.addBook(bookDTO);
        if (count<1){
            return Result.error("新增书籍失败");
        }

        return Result.success("新增书籍成功");
    }

    @Operation(summary = "修改书籍")
    @PutMapping("/update")
    public Result updateBook(@RequestBody @Validated BookDTO bookDTO){
        //判断是否存在图书
        Book findBook = bookService.findBookById(bookDTO.getId());
        if (findBook == null){
            return Result.error("图书不存在");
        }
        //图书存在更新图书
        Integer count = bookService.updateBook(bookDTO);
        if (count<1){
            return Result.error("更新失败");
        }
        return Result.success("更新成功");
    }

    @Operation(summary = "删除图书")
    @DeleteMapping("/delete")
    public Result deleteBook(Integer bookid){
        //判断图书是否存在
        Book findBook = bookService.findBookById(bookid);
        if (findBook == null){
            return Result.error("图书不存在");
        }
        //删除图书
        Integer count = bookService.deleteBook(bookid);
        if (count<1){
            return Result.error("删除失败");
        }
        return Result.success("删除成功");
    }
}
