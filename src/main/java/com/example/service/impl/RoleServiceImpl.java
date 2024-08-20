package com.example.service.impl;

import com.example.persistence.entity.security.Role;
import com.example.persistence.repository.security.RoleRepository;
import com.example.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.lang.module.ResolutionException;

@Service
public class RoleServiceImpl implements RoleService {

    @Value("${security.default.role}")
    private  String defaultRole;
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Role findDefaultRole() {
        return roleRepository.findByName(defaultRole)
                .orElseThrow(()->new ResolutionException("Role not found. Default Role"));
    }
}
