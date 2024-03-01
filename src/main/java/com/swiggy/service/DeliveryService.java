package com.swiggy.service;

import com.swiggy.entities.DeliveryAddress;


public interface DeliveryService {

    boolean saveDeliveryAddress(DeliveryAddress shippingAddress, String id);
}
