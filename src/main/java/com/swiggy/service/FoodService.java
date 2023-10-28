package com.swiggy.service;

import com.swiggy.entities.Food;

import java.util.List;
import java.util.Map;

public interface FoodService {

    public Food addFood(Food food);
    public Food getFoodById(Long id);

    public List<Food> getAllFoods();
    public Food deleteFoodById(Long id);

    public  List<Map<String, Object>> getFoodByRestaurantId(Long id);

    public List<Food> searchFoodByAlpha(String alpha);

    public List<Food> SearchFoodByCostLowToHigh();

    public List<Food> searchFoodByRating();

    public List<Food> listNewFood();

    public List<Food> getFourPlusRatingFood();

}
