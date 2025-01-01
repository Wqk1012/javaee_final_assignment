package com.example.shopping.controller.admin;

import com.example.shopping.pojo.Author;
import com.example.shopping.pojo.Result;
import com.example.shopping.service.AuthorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/author")
@Tag(name = "作家管理", description = "提供作家管理相关操作的API")
public class AdminAuthorController {
    @Autowired
    private AuthorService authorService;

    @PostMapping("/add")
    @Operation(summary = "新增作家")
    public Result add(Author author){
        String name = author.getName();
        Author authorByName = authorService.getAuthorByName(name);
        if (authorByName != null){
            return Result.error("作家已存在");
        }
        //作家不存在就新增
        Integer count = authorService.insertAuthor(author);
        if (count<1){
            return Result.error("新增失败");
        }
        return Result.success("新增成功");
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除作家")
    public Result delete(Integer authorId){
        Author authorById = authorService.getAuthorById(authorId);
        if (authorById == null){
            return Result.error("作家不存在");
        }
        Integer count = authorService.deleteAuthorById(authorId);
        if (count<1){
            return Result.error("删除失败");
        }
        return Result.success("删除成功");
    }

    @PutMapping("/update")
    @Operation(summary = "更新作家")
    public Result update(Author author){
        String name = author.getName();
        Author authorByName = authorService.getAuthorByName(name);
        if (authorByName != null){
            return Result.error("作家已经存在");
        }
        Integer count = authorService.updateAuthor(author);
        if (count<1){
            return Result.error("更改失败");
        }
        return Result.success("更改成功");
    }
}
