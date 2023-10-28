package com.swiggy.repo;

import com.swiggy.entities.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface FoodRepo extends JpaRepository<Food, Long> {

//  @Query(value = "select * from food where category_id in (select id from category where restaurant_id=?1)", nativeQuery = true)
    @Query(value = "SELECT f.*, c.* FROM food f INNER JOIN category c ON f.category_id = c.id WHERE c.restaurant_id = ?1", nativeQuery = true)
    List<Map<String, Object>> getFoodByRestaurantId(Long id);


    //double slash is used to escape the  backslash itself.
    @Query(value = "SELECT * FROM food WHERE food_name LIKE '%\\?1%'", nativeQuery = true)
    List<Food> searchFoodByAlphabet(String alpha);


    @Query(value = "select * from food order by food_price asc", nativeQuery = true)
    List<Food> searchFoodByCostLowToHigh();


    @Query(value = "select * from food order by food_rating desc", nativeQuery = true)
    List<Food> searchFoodByTopRatings();


    @Query(value = "select * from food where food_rating >= 4", nativeQuery = true)
    List<Food> searchFourPlusRatingFood();



    @Query(value = "select * from food order by added_time desc", nativeQuery = true)
    List<Food> searchFoodByLatestTiming();
}
