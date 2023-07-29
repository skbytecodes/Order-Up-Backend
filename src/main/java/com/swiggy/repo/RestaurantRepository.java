package com.swiggy.repo;

import com.swiggy.entities.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

    @Query(value = "select distinct * from restaurant ", nativeQuery = true)
    List<Restaurant> findAllRestaurants();
}