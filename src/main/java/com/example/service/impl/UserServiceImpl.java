package com.example.service.impl;

import com.example.dto.request.SaveUser;
import com.example.exceptions.ResourceNotFoundException;
import com.example.mapper.UserMapper;
import com.example.persistence.entity.security.User;
import com.example.persistence.repository.security.UserRepository;
import com.example.service.RoleService;
import com.example.service.UserService;
import com.example.service.validator.PasswordValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder   passwordEncoder;
    private final RoleService roleService;
    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, RoleService roleService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleService = roleService;
    }

    @Override
    public User registerOneCustomer(SaveUser saveUser) {
        PasswordValidator.validatePassword(saveUser.password(),saveUser.repeatedPassword());
        User savedUser = UserMapper.toGetEntity(saveUser,passwordEncoder.encode(saveUser.password()),roleService.findDefaultRole());
        return userRepository.save(savedUser);
    }

    @Override
    public User findOneByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(()-> new ResourceNotFoundException("User not found whit username:"+username));
    }


}
