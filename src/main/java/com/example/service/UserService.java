package com.example.service;

import com.example.dto.request.SaveUser;
import com.example.persistence.entity.security.User;
import org.springframework.data.jpa.repository.Query;

public interface UserService {

    User registerOneCustomer(SaveUser saveUser);

    User findOneByUsername (String username);
}
