package com.example.service;

import com.example.dto.request.SavePermission;
import com.example.dto.response.ShowPermission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface PermissionService {
    Page<ShowPermission> findAll(Pageable pageable);
    ShowPermission findById (Integer permissionId);
    ShowPermission createOne (SavePermission savePermission);
    void deleteOneById (Integer permissionId);

}
