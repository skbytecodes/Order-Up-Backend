package com.swiggy.controllers;

import com.swiggy.entities.DeliveryAddress;
import com.swiggy.service.DeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
public class DeliveryController {

    @Autowired
    private DeliveryService deliveryService;

    @PostMapping("/updateDeliveryAddress/{userId}")
    public ResponseEntity<?> updateDeliveryAddress(
            @Valid @RequestBody DeliveryAddress deliveryAddress,
            BindingResult result,
            @PathVariable("userId") String id
        ){
        if (result.hasErrors()) {
            List<String> errors = new ArrayList<>();
            for (FieldError error : result.getFieldErrors()) {
                errors.add(error.getField() + ": " + error.getDefaultMessage());
            }
            for (String error : errors){
                System.out.println(error);
            }
            throw new RuntimeException();
        }
        boolean isSaved = deliveryService.saveDeliveryAddress(deliveryAddress,id);
        if(isSaved)
            return new ResponseEntity<>("Successfully Saved", HttpStatus.OK);
        else
            return new ResponseEntity<>("Something Went Wrong", HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @GetMapping("/deliveryAddressesByUserid/{id}")
    public ResponseEntity<?> getAllDeliveryAddressesByUserId(@PathVariable("id") String userId){

        return null;
    }
}
