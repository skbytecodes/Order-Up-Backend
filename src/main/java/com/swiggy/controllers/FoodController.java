package com.swiggy.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.swiggy.entities.Category;
import com.swiggy.entities.Food;
import com.swiggy.service.CategoryService;
import com.swiggy.serviceImpl.FoodServiceImpl;
import com.swiggy.util.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/v1")
public class FoodController {

    @Autowired
    private FoodServiceImpl foodService;

    @Autowired
    private Utility utility;


    @Autowired
    private CategoryService categoryService;

    @PostMapping("/food")
    public ResponseEntity<?> addFood(@RequestParam("file") MultipartFile file, String data) {
        Food food = null;
        try {
            ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule());
            food = mapper.readValue(data, Food.class);

        } catch (JsonMappingException e) {
            e.printStackTrace();
            return new ResponseEntity<>("JsonMappingException", HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return new ResponseEntity<>("JsonProcessingException", HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Something Went Wrong", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        try {
            Category categoryById = categoryService.getCategoryById(food.getCategory().getId());
            if(categoryById == null){
                return new ResponseEntity<>("Bad Request", HttpStatus.BAD_REQUEST);
            }
            food.setCategory(categoryById);
            String imageUrl = utility.saveImage(file);
            food.setImageUrl(imageUrl);
            food.setImageName(file.getOriginalFilename());
            food.setAddedTime(new Timestamp(System.currentTimeMillis()));
            foodService.addFood(food);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Something Went Wrong", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(food, HttpStatus.OK);
    }





    @GetMapping("/foodById/{id}")
    public ResponseEntity<?> getFoodById(@PathVariable("id") Long id){
        try {
            Food foodById = foodService.getFoodById(id);
            return new ResponseEntity<>(foodById, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    @GetMapping("/foods")
    public ResponseEntity<?> findAllFoods(){
        try {
            List<Food> foods = foodService.getAllFoods();
            return new ResponseEntity<>(foods, HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @DeleteMapping("/food/{id}")
    public ResponseEntity<?> deleteFoodById(@PathVariable("id") Long id){
        try {
            Food food = foodService.deleteFoodById(id);
            return new ResponseEntity<>(food, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    @GetMapping("/food/newFood")
    public ResponseEntity<?> listAllNewFood(){
        try {
            List<Food> foods = foodService.listNewFood();
            return new ResponseEntity<>(foods, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("Something Went Wrong", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/restaurants/food/{id}")
    public ResponseEntity<?> foodByRestaurantId(@PathVariable("id") Long restId){
        try {
            List<Map<String, Object>> foodByRestaurantId = foodService.getFoodByRestaurantId(restId);
            return new ResponseEntity<>(foodByRestaurantId, HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("Something Went Wrong", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
