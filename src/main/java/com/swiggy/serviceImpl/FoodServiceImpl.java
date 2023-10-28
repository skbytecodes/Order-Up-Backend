package com.swiggy.serviceImpl;

import com.swiggy.entities.Food;
import com.swiggy.repo.FoodRepo;
import com.swiggy.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class FoodServiceImpl implements FoodService {

    @Autowired
    private FoodRepo foodRepo;
    @Override
    public Food addFood(Food food) {
       if(food != null){
           Food savedFood = foodRepo.save(food);
           return savedFood;
       }
       return null;
    }

    @Override
    public Food getFoodById(Long id) {
        if(id != null){
            Food food = foodRepo.findById(id).get();
            return food;
        }
        return null;
    }

    @Override
    public List<Food> getAllFoods() {
        return foodRepo.findAll();
    }

    @Override
    public Food deleteFoodById(Long id) {
        if(id != null){
            Food food = foodRepo.findById(id).get();
            foodRepo.deleteById(id);
            return food;
        }
        return null;
    }

    @Override
    public  List<Map<String, Object>> getFoodByRestaurantId(Long id) {
        if(id != null){
            return foodRepo.getFoodByRestaurantId(id);
        }
        return null;
    }

    @Override
    public List<Food> searchFoodByAlpha(String alpha) {
        if(alpha != null){
            List<Food> foodByAlphas = foodRepo.searchFoodByAlphabet(alpha);
            return foodByAlphas;
        }
        return null;
    }

    @Override
    public List<Food> SearchFoodByCostLowToHigh() {
        return foodRepo.searchFoodByCostLowToHigh();
    }

    @Override
    public List<Food> searchFoodByRating() {
        return foodRepo.searchFoodByTopRatings();
    }

    @Override
    public List<Food> listNewFood() {
        try {
            return foodRepo.searchFoodByLatestTiming();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Food> getFourPlusRatingFood() {
        return foodRepo.searchFourPlusRatingFood();
    }
}
