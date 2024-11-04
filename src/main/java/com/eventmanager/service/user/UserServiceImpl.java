package com.eventmanager.service.user;

import com.eventmanager.dto.UserDTO;
import com.eventmanager.entity.User;
import com.eventmanager.mapper.UserMapper;
import com.eventmanager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userMapper = userMapper;
    }

    @Override
    public boolean isUserRegistered(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

    @Override
    public void registerUser(UserDTO userDTO) {
        User newUser = userMapper.toUser(userDTO);
        String hashedPassword = passwordEncoder.encode(userDTO.getPassword());
        newUser.setPassword(hashedPassword);

        userRepository.save(newUser);
    }


}
