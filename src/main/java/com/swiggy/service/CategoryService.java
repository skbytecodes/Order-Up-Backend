package com.swiggy.service;

import com.swiggy.entities.Category;

import java.util.List;

public interface CategoryService {

    public Category getCategoryById(Long id);

    public Category addCategory (Category category);

    public List<Category> getAllCategories();

    public Category deleteCategoryById(Long id);

    public List<Category> getAllCategoriesByRestaurantId(Long id);

}
