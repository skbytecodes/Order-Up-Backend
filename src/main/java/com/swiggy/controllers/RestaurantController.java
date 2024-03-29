package com.swiggy.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.swiggy.entities.Restaurant;
import com.swiggy.service.RestaurantService;
import com.swiggy.util.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/v1")
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private Utility util;


    @GetMapping("/restaurant/{id}")
    public ResponseEntity<Restaurant> getRestaurantById(@PathVariable("id") Long id) {
        Restaurant restaurantById = restaurantService.getRestaurantById(id);
        return new ResponseEntity<>(restaurantById, HttpStatus.OK);
    }


    @PostMapping("/restaurant")
    public ResponseEntity<?> saveRestaurant(@RequestParam("file") MultipartFile file, @RequestParam("data") String data) {
        Restaurant response = null;
        Restaurant restaurant = null;
        try {
            ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule());
            restaurant = mapper.readValue(data, Restaurant.class);

        }catch (JsonMappingException e){
            e.printStackTrace();
            return new ResponseEntity<>("JsonMappingException", HttpStatus.INTERNAL_SERVER_ERROR);
        }catch (JsonProcessingException e){
            e.printStackTrace();
            return new ResponseEntity<>("JsonProcessingException", HttpStatus.INTERNAL_SERVER_ERROR);
        }catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Something Went Wrong", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        try {
            String imageUrl = util.saveImage(file);
            restaurant.setImageName(file.getOriginalFilename());
            restaurant.setImageUrl(imageUrl);
            response = restaurantService.saveRestaurant(restaurant);
            if (response != null) {
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Something Went Wrong", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Something Went Wrong", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PutMapping("/restaurant")
    public ResponseEntity<?> updateRestaurant(@RequestParam("data") String data) {
        Restaurant restaurant = null;
        Restaurant updated = null;
        try {
            ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule());
            restaurant = mapper.readValue(data, Restaurant.class);
            updated = restaurantService.saveRestaurant(restaurant);
        } catch (JsonMappingException e) {
            e.printStackTrace();
            return new ResponseEntity<>("Entry failed-JsonMappingException", HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return new ResponseEntity<>("Entry failed-JsonProcessingException", HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Something Went Wrong", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @DeleteMapping("/restaurant/{id}")
    public ResponseEntity<?> saveSwiggyRestaurant(@PathVariable("id") Long id) {
        if (id != null) {
            Restaurant restaurant = null;
            try {
                restaurant = restaurantService.deleteRestaurantById(id);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return new ResponseEntity<>(restaurant, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Bad request", HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping("/restaurants")
    public ResponseEntity<?> getAllRestaurants() {
        List<Restaurant> restaurants = null;
        try {
            restaurants = restaurantService.getAllRestaurants();
            restaurants.forEach(restaurant -> restaurant.getImageUrl().replace("\\","/"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(restaurants, HttpStatus.OK);
    }


    @GetMapping("/totalRestaurants")
    public int countTotalRestaurants(){
        return restaurantService.countTotalRestaurants();
    }


    @GetMapping("/findRestaurant/{str}")
    public List<Restaurant> getAllRestaurantsByChar(@PathVariable("str") String str){
        try {
            if (str != null || !str.isEmpty()){
                return restaurantService.getAllTheRestaurantsByChar(str);
            }else
                return null;
        }catch (Exception e){
            return null;
        }
    }



    @GetMapping("/login")
    public ResponseEntity<Boolean> authenticate(){
        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    @GetMapping("/register")
    public ResponseEntity<Boolean> registration(){
        return new ResponseEntity<>(true, HttpStatus.OK);
    }
}
