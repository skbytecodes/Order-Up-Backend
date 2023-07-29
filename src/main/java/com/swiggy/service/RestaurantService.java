package com.swiggy.service;


import com.swiggy.entities.Restaurant;
import org.springframework.http.ResponseEntity;

public interface RestaurantService {

    public Restaurant getRestaurantById(Long id);
    public Restaurant saveRestaurant(Restaurant restaurant);
    public Restaurant deleteRestaurantById(Long id);

}
