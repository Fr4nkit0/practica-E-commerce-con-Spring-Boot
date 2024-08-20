package com.example.persistence.repository.security;

import com.example.persistence.entity.security.GrantedPermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface  PermissionRepository extends JpaRepository<GrantedPermission,Integer> {
}
