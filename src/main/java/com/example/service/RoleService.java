package com.example.service;

import com.example.persistence.entity.security.Operation;
import com.example.persistence.entity.security.Role;

public interface RoleService {
    Role findDefaultRole ();
}
