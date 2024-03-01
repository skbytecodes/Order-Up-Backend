package com.swiggy.serviceImpl;

import com.swiggy.entities.DeliveryAddress;
import com.swiggy.entities.User;
import com.swiggy.repo.DeliveryAddressRepository;
import com.swiggy.service.DeliveryService;
import com.swiggy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeliveryServiceImpl implements DeliveryService {


    @Autowired
    private DeliveryAddressRepository deliveryAddressRepository;
    @Autowired
    private UserService userService;

    @Override
    public boolean saveDeliveryAddress(DeliveryAddress shippingAddress, String id) {
        if (shippingAddress != null ){
            Long userId =  Long.parseLong(id);
            User user = userService.getUserById(userId);
            if(user != null){
                deliveryAddressRepository.save(shippingAddress);
            }
        }
        return false;
    }
}
