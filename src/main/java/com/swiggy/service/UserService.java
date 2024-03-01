package com.swiggy.service;

import com.swiggy.dtos.UserDto;
import com.swiggy.entities.User;

public interface UserService {
    boolean saveUser(UserDto userDto);

    User getUserById(Long userId);

    boolean deleteUserById(Long userId);
}
