package com.example.shopping.service;

import com.example.shopping.pojo.Category;

import java.util.List;

public interface CategoryService {

    // 插入分类
    Integer insertCategory(Category category);

    // 删除分类
    Integer deleteCategoryById(Integer id);

    // 更新分类
    Integer updateCategory(Category category);

    // 查询所有分类
    List<Category> getAllCategories();
    Category getCategoryById(Integer id); // 根据 ID 查询分类
    Category getCategoryByName(String name); // 根据名字查询分类
}
