package com.swiggy.service;


import com.swiggy.entities.Restaurant;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface RestaurantService {

    public Restaurant getRestaurantById(Long id);
    public Restaurant saveRestaurant(Restaurant restaurant);
    public Restaurant deleteRestaurantById(Long id);

    public List<Restaurant> getAllRestaurants();
    public int countTotalRestaurants();

    public List<Restaurant> getAllTheRestaurantsByChar(String str);

}
