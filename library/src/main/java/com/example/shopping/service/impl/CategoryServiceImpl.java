package com.example.shopping.service.impl;

import com.example.shopping.mapper.CategoryMapper;
import com.example.shopping.pojo.Category;
import com.example.shopping.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public Category getCategoryById(Integer id) {
        return categoryMapper.getCategoryById(id);
    }

    @Override
    public Category getCategoryByName(String name) {
        return categoryMapper.getCategoryByName(name);
    }

    // 插入分类
    @Override
    public Integer insertCategory(Category category) {
        return categoryMapper.insertCategory(category); // 返回插入的行数
    }

    // 删除分类
    @Override
    public Integer deleteCategoryById(Integer id) {
        return categoryMapper.deleteCategoryById(id); // 返回删除的行数
    }

    // 更新分类
    @Override
    public Integer updateCategory(Category category) {
        return categoryMapper.updateCategory(category); // 返回更新的行数
    }

    // 查询所有分类
    @Override
    public List<Category> getAllCategories() {
        return categoryMapper.getAllCategories();
    }
}
