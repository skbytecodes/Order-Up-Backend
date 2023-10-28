package com.swiggy.serviceImpl;


import com.swiggy.entities.Category;
import com.swiggy.repo.CategoryRepo;
import com.swiggy.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepo categoryRepo;
    @Override
    public Category getCategoryById(Long id) {
        try {
            return categoryRepo.findById(id).get();
        }catch (Exception e){
            return null;
        }

    }

    @Override
    public Category addCategory(Category category) {
        Category savedCategory = null;
        try {

            if(category != null){
                 savedCategory = categoryRepo.save(category);
            }
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
        return savedCategory;
    }

    @Override
    public List<Category> getAllCategories() {
        try {
           return  categoryRepo.findAll();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Category deleteCategoryById(Long id) {
       Category cat = null;
        try {

             cat = categoryRepo.findById(id).get();
             if(cat != null){
                 categoryRepo.deleteById(id);
             }
        }catch (Exception e){
           return null;
       }
        return cat;
    }

    @Override
    public List<Category> getAllCategoriesByRestaurantId(Long id) {
       List <Category> categories = null;
        try {
            categories = categoryRepo.findAllCategoriesByRestaurantId(id);
        }catch (Exception e){
           e.printStackTrace();
           return null;
       }
        return categories;
    }
}
