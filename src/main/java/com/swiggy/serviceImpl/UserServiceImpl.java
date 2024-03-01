package com.swiggy.serviceImpl;

import com.swiggy.dtos.UserDto;
import com.swiggy.entities.DeliveryAddress;
import com.swiggy.entities.User;
import com.swiggy.repo.UserRepository;
import com.swiggy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Override
    public boolean saveUser(UserDto userDto) {
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());
        user.setPhone(userDto.getPhone());
        user.setEmail(userDto.getEmail());
        DeliveryAddress address = new DeliveryAddress();
        address.setHouseNo(userDto.getHouseNo());
        address.setPinCode(userDto.getPinCode());
        address.setStreetAddress(userDto.getStreetAddress());
        address.setDeliveryInstructions(userDto.getDeliveryInstructions());
        address.setLandMark(userDto.getLandMark());
        address.setRecipientName(userDto.getRecipientName());
        user.setDeliveryAddress(address);
        try {
            userRepository.save(user);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public User getUserById(Long userId) {
        try {
            User user =  userRepository.findById(userId).get();
            return user;
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public boolean deleteUserById(Long userId) {
        User user = userRepository.findById(userId).get();
        if(user != null){
            userRepository.delete(user);
            return true;
        }
        return false;
    }
}
