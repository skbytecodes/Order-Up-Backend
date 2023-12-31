package com.swiggy.repo;

import com.swiggy.entities.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

    @Query(value = "select distinct * from restaurant order by restaurant_name asc", nativeQuery = true)
    List<Restaurant> findAllRestaurants();


    @Query(value = "select count(1) from restaurant", nativeQuery = true)
    int findTotalNumberOfRestaurants();


    @Query(value = "SELECT * FROM restaurant WHERE restaurant_name LIKE ?1%", nativeQuery = true)
    List<Restaurant> findRestaurantByChar(String str);
}