package com.swiggy.entities;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
public class Food {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long foodId;
    private String foodName;
    private String foodDescription;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(
            name = "category_id",
            referencedColumnName = "id"
    )
    private Category category;

    private Double foodPrice;

    private Double foodRating;

    private String imageUrl;
    private String imageName;

    private Timestamp addedTime;

    public Long getFoodId() {
        return foodId;
    }

    public void setFoodId(Long foodId) {
        this.foodId = foodId;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getFoodDescription() {
        return foodDescription;
    }

    public void setFoodDescription(String foodDescription) {
        this.foodDescription = foodDescription;
    }

    public Double getFoodPrice() {
        return foodPrice;
    }

    public void setFoodPrice(Double foodPrice) {
        this.foodPrice = foodPrice;
    }

    public Double getFoodRating() {
        return foodRating;
    }

    public void setFoodRating(Double foodRating) {
        this.foodRating = foodRating;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }


    public Timestamp getAddedTime() {
        return addedTime;
    }

    public void setAddedTime(Timestamp addedTime) {
        this.addedTime = addedTime;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Food{" +
                "foodId=" + foodId +
                ", foodName='" + foodName + '\'' +
                ", foodDescription='" + foodDescription + '\'' +
                ", foodPrice=" + foodPrice +
                ", foodRating=" + foodRating +
                ", imageUrl='" + imageUrl + '\'' +
                ", imageName='" + imageName + '\'' +
                ", addedTime=" + addedTime +
                '}';
    }
}
