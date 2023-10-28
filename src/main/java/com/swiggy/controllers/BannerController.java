package com.swiggy.controllers;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.swiggy.entities.Banner;
import com.swiggy.service.BannerService;
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
public class BannerController {

    @Autowired
    private BannerService bannerService;


    @Autowired
    private Utility util;


    @PostMapping("/banner")
    public ResponseEntity<Object> addBanner(@RequestParam("file") MultipartFile file, String data) {
        Banner banner = null;
        try {
            ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule());
            banner = mapper.readValue(data, Banner.class);

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

            String imageUrl = util.saveImage(file);
            banner.setImageUrl(imageUrl);
            banner.setImageName(file.getOriginalFilename());

            bannerService.addBanner(banner);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Something Went Wrong", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(banner, HttpStatus.OK);
    }


    @GetMapping("/bannerById/{id}")
    public Banner getBannerById(Long id) {
        return bannerService.getBannerById(id);
    }


    @GetMapping("/banners")
    public List<Banner> getAllBanners() {
        return bannerService.getAllBanners();
    }


    @DeleteMapping("/deleteBanner/{id}")
    public Banner deleteBannerById(Long id) {
        return bannerService.deleteBannerById(id);
    }


}
