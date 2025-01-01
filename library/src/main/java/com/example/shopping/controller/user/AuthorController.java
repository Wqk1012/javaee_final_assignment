package com.example.shopping.controller.user;

import com.example.shopping.pojo.Author;
import com.example.shopping.pojo.Result;
import com.example.shopping.service.AuthorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user/Author")
@Tag(name = "作家查询", description = "提供作家查询相关操作的API")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @Operation(summary = "获取全部作家")
    @GetMapping("/findAll")
    public Result<List<Author>> findAll(){
        return Result.success(authorService.getAllAuthors());
    }

    @Operation(summary = "通过id获取作家")
    @GetMapping("/findAuthorById")
    public Result<Author> findAuthorById(Integer authorId){
        Author authorById = authorService.getAuthorById(authorId);
        if (authorById == null){
            return Result.error("作家不存在");
        }
        return Result.success(authorById);
    }
}
