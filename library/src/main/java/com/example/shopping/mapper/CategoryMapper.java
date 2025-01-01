package com.example.shopping.mapper;

import com.example.shopping.pojo.Category;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CategoryMapper {
    Integer insertCategory(Category category); // 插入分类

    Integer deleteCategoryById(Integer id); // 删除分类

    Integer updateCategory(Category category); // 更新分类

    Category getCategoryById(Integer id); // 根据 ID 查询分类

    List<Category> getAllCategories(); // 查询所有分类

    Category getCategoryByName(String name); // 根据名字查询分类
}
