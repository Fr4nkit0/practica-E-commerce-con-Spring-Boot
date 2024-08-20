package com.example.mapper;

import com.example.dto.request.SaveUser;
import com.example.dto.response.GetUser;
import com.example.dto.response.RegisteredUser;
import com.example.persistence.entity.security.Role;
import com.example.persistence.entity.security.User;

public class UserMapper {

    public static RegisteredUser toGetRegisteredUser (User entity , String jwt){
        if (entity==null) return null;
        return new RegisteredUser(
                entity.getId(),
                entity.getUsername(),
                jwt,
                entity.getRole().getName(),
                entity.getEmail(),
                entity.getPhoneNumber()
        );
    }
    public static GetUser toGetDto (User entity){
        if (entity==null) return null;
        return new GetUser(
                entity.getName(),
                entity.getUsername(),
                entity.getEmail(),
                entity.getPhoneNumber()
        );
    }
    public static User toGetEntity (SaveUser saveUser , String password , Role role){
        if (saveUser==null)return null;
        return User.builder()
                .name(saveUser.name())
                .username(saveUser.username())
                .password(password)
                .role(role)
                .phoneNumber(saveUser.phoneNumber())
                .email(saveUser.email())
                .build();
    }
}
