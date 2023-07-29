package com.swiggy.util;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Component
public class Utility {

    public String saveImage(MultipartFile file) {
        try {
            String directoryPath = "D:\\uploaded_images\\";
            File directory = new File(directoryPath);
            if (!directory.exists()) {
                directory.mkdirs();
            }
            String imageFilePath = directory + file.getOriginalFilename();
            File imageFile = new File(imageFilePath);
            file.transferTo(imageFile);
            return imageFilePath;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}