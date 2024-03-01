package com.swiggy.controllers;

import com.swiggy.dtos.UserDto;
import com.swiggy.entities.User;
import com.swiggy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.Valid;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody UserDto userDto, BindingResult result){

        if(result.hasErrors()){
            List<String> errors = new ArrayList<>();
            for(FieldError error : result.getFieldErrors()){
                errors.add(error.getField() + ": " + error.getDefaultMessage());
            }
            for (String error : errors){
                System.out.println(error);
            }
            throw new RuntimeException();
        }
        boolean isSaved = userService.saveUser(userDto);
        if(isSaved)
            return new ResponseEntity<>("Successfully registered", HttpStatus.OK);
        else
            return new ResponseEntity<>("Something Went Wrong", HttpStatus.INTERNAL_SERVER_ERROR);
    }



    @GetMapping("/user/{id}")
    public ResponseEntity<?> getUserById(@PathVariable("id") String id){
        Long userId = null;
        try {
            userId = Long.parseLong(id);
        }catch (NumberFormatException e){
            return new ResponseEntity<>("User not Found", HttpStatus.OK);
        }
        User user = userService.getUserById(userId);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }


    @DeleteMapping("/user/{id}")
    public ResponseEntity<?> deleteUserById(@PathVariable("id") String id){
        Long userId = Long.parseLong(id);
        try {
            userService.deleteUserById(userId);
        }catch (NumberFormatException e){
            return new ResponseEntity<>("User not Found", HttpStatus.OK);
        }
        return new ResponseEntity<>("User Deleted Successfully", HttpStatus.OK);
    }

}
