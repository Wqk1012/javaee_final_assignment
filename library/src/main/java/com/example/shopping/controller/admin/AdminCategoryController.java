package com.example.shopping.controller.admin;

import com.example.shopping.pojo.Category;
import com.example.shopping.pojo.Result;
import com.example.shopping.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/Category")
@Tag(name = "书籍分类管理", description = "提供书籍分类管理相关操作的API")
public class AdminCategoryController {
    @Autowired
    private CategoryService categoryService;
    /**
     * 添加新的书籍分类
     *
     * @param category 新分类的信息
     * @return 成功或失败的结果信息
     */
    @Operation(summary = "添加新分类")
    @PostMapping("/add")
    public Result addCategory(@RequestBody Category category) {
        String name = category.getName();
        Category categoryByName = categoryService.getCategoryByName(name);
        if ( categoryByName != null){
            return Result.error("分类已存在");
        }

        Integer result = categoryService.insertCategory(category);
        if (result < 1) {
            return Result.error("添加分类失败，请重试");
        }
        return Result.success("成功添加分类：" + category.getName());
    }

    /**
     * 删除指定 ID 的书籍分类
     *
     * @param id 要删除的分类 ID
     * @return 成功或失败的结果信息
     */
    @Operation(summary = "删除分类")
    @DeleteMapping("/delete/{id}")
    public Result deleteCategory(@PathVariable Integer id) {
        Category findCategory = categoryService.getCategoryById(id);
        if (findCategory == null) {
            return Result.error("分类不存在");
        }
        Integer result = categoryService.deleteCategoryById(id);
        if (result < 1) {
            return Result.error("删除分类失败，请重试");
        }
        return Result.success("成功删除分类：" + findCategory.getName());
    }

    /**
     * 更新书籍分类信息
     *
     * @param category 更新后的分类信息
     * @return 成功或失败的结果信息
     */
    @Operation(summary = "更新分类")
    @PutMapping("/update/{id}")
    public Result updateCategory(@RequestBody Category category) {
        Integer id = category.getId();
        Category findCategory = categoryService.getCategoryById(id);
        if (findCategory == null) {
            return Result.error("分类不存在");
        }
        category.setId(id); // 确保传入的分类 ID 与路径参数一致
        Integer result = categoryService.updateCategory(category);
        if (result < 1) {
            return Result.error("更新分类失败，请重试");
        }
        return Result.success("成功更新分类：" + category.getName());
    }

}
