package com.swiggy.serviceImpl;

import com.swiggy.entities.Restaurant;
import com.swiggy.repo.RestaurantRepository;
import com.swiggy.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class RestaurantServiceImpl implements RestaurantService {
    @Autowired
    private RestaurantRepository repo;


    @Override
    public Restaurant getRestaurantById(Long id) {
        return repo.findById(id).get();
    }

    @Override
    public Restaurant saveRestaurant(Restaurant restaurant) {
        repo.save(restaurant);
        return null;
    }

    @Override
    public Restaurant deleteRestaurantById(Long id) {
        return null;
    }

    @Override
    public Restaurant updateRestaurantById(Long id, String data) {
        return null;
    }
}
