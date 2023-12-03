package com.swiggy.dtos;

public class PaymentVerification {
    private String orderCreationId;
    private String razorpayPaymentId;
    private String razorpayOrderId;
    private String razorpaySignature;

    public String getOrderCreationId() {
        return orderCreationId;
    }

    public void setOrderCreationId(String orderCreationId) {
        this.orderCreationId = orderCreationId;
    }

    public String getRazorpayPaymentId() {
        return razorpayPaymentId;
    }

    public void setRazorpayPaymentId(String razorpayPaymentId) {
        this.razorpayPaymentId = razorpayPaymentId;
    }

    public String getRazorpayOrderId() {
        return razorpayOrderId;
    }

    public void setRazorpayOrderId(String razorpayOrderId) {
        this.razorpayOrderId = razorpayOrderId;
    }

    public String getRazorpaySignature() {
        return razorpaySignature;
    }


    public void setRazorpaySignature(String razorpaySignature) {
        this.razorpaySignature = razorpaySignature;
    }


    @Override
    public String toString() {
        return "PaymentVerification{" +
                "orderCreationId='" + orderCreationId + '\'' +
                ", razorpayPaymentId='" + razorpayPaymentId + '\'' +
                ", razorpayOrderId='" + razorpayOrderId + '\'' +
                ", razorpaySignature='" + razorpaySignature + '\'' +
                '}';
    }
}
