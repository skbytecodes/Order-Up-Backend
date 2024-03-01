package com.swiggy.dtos;

import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.*;

public class UserDto {

    @NotEmpty(message = "Username can not be empty")
    private String username;

    @NotEmpty(message = "Password can not be empty")
    private String password;

    @NotEmpty(message = "Email can not be empty")
    @Email(message = "Please enter the valid email address")
    private String email;

    @NotEmpty(message = "Phone no can not be empty")
    private String phone;

    @NotEmpty(message = "Name ca not be empty")
    @Min(value = 3)
    private String recipientName;


    @NotEmpty(message = "House no can not be null")
    @Size(min = 1, max = 10, message = "The house no must be between 5 and 20 characters long.")
    private String houseNo;

    @NotEmpty(message = "Street cannot be empty")
    private String streetAddress;

    @Range(min = 100000, max = 999999, message = "Please enter the correct PinCode")
    private int pinCode;

    private String landMark;

    private String deliveryInstructions;

    @NotEmpty(message = "Delivery location can not empty")
    private String deliveryLocation;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRecipientName() {
        return recipientName;
    }

    public void setRecipientName(String recipientName) {
        this.recipientName = recipientName;
    }

    public String getHouseNo() {
        return houseNo;
    }

    public void setHouseNo(String houseNo) {
        this.houseNo = houseNo;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public int getPinCode() {
        return pinCode;
    }

    public void setPinCode(int pinCode) {
        this.pinCode = pinCode;
    }

    public String getLandMark() {
        return landMark;
    }

    public void setLandMark(String landMark) {
        this.landMark = landMark;
    }

    public String getDeliveryInstructions() {
        return deliveryInstructions;
    }

    public void setDeliveryInstructions(String deliveryInstructions) {
        this.deliveryInstructions = deliveryInstructions;
    }

    public String getDeliveryLocation() {
        return deliveryLocation;
    }

    public void setDeliveryLocation(String deliveryLocation) {
        this.deliveryLocation = deliveryLocation;
    }
}
