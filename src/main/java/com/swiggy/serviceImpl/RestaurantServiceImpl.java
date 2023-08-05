package com.swiggy.serviceImpl;

import com.swiggy.entities.Restaurant;
import com.swiggy.repo.RestaurantRepository;
import com.swiggy.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class RestaurantServiceImpl implements RestaurantService {
    @Autowired
    private RestaurantRepository repo;


    @Override
    public Restaurant getRestaurantById(Long id) {
        try {
            return repo.findById(id).get();
        }catch (NoSuchElementException e){
            return null;
        }
    }

    @Override
    public Restaurant saveRestaurant(Restaurant restaurant) {
        Restaurant saved = repo.save(restaurant);
        return saved;
    }

    @Override
    public Restaurant deleteRestaurantById(Long id) {
        Restaurant restaurant = repo.findById(id).get();
        repo.deleteById(id);
        return restaurant;
    }

    @Override
    public List<Restaurant> getAllRestaurants() {
         return repo.findAllRestaurants();
    }

    @Override
    public int countTotalRestaurants() {
        try {
            return repo.findTotalNumberOfRestaurants();
        }catch (Exception e){
            return 0;
        }
    }

}
