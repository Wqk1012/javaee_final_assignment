package com.example.shopping.controller.user;

import com.example.shopping.pojo.Category;
import com.example.shopping.pojo.Result;

import com.example.shopping.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/user/Category")
@Tag(name = "书籍分类查询", description = "提供书籍分类查询相关操作的API")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @Operation(summary = "查询所有分类")
    @GetMapping("/findAll")
    public Result<List<Category>> findAll(){
        List<Category> allCategories = categoryService.getAllCategories();
        return Result.success(allCategories);
    }
}
