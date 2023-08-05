package com.swiggy.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Component
public class Utility {

    @Value("${data.file.path}")
    private String directoryPath;
    public String saveImage(MultipartFile file) {
        try {
            File directory = new File(directoryPath);
            if (!directory.exists()) {
                directory.mkdirs();
            }
            String imageFilePath = directory +"\\"+ file.getOriginalFilename();
            System.out.println("image file path "+imageFilePath);
            File imageFile = new File(imageFilePath);
            System.out.println("image file path "+imageFile);
            file.transferTo(imageFile);
            return imageFilePath;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}