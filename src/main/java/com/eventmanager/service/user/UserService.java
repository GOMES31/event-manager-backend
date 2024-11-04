package com.eventmanager.service.user;

import com.eventmanager.dto.UserDTO;
import com.eventmanager.entity.User;

public interface UserService {

    boolean isUserRegistered(String email);

    void registerUser(UserDTO userDTO);
}
