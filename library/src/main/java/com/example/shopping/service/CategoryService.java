package com.example.shopping.service;

import com.example.shopping.pojo.Category;

public interface CategoryService {
    Category getCategoryById(Integer id); // 根据 ID 查询分类
    Category getCategoryByName(String name); // 根据名字查询分类
}
