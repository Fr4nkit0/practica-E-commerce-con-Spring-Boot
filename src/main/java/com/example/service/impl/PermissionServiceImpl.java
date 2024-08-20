package com.example.service.impl;

import com.example.dto.request.SavePermission;
import com.example.dto.response.ShowPermission;
import com.example.exceptions.ResourceNotFoundException;
import com.example.mapper.PermissionMapper;
import com.example.persistence.entity.security.GrantedPermission;
import com.example.persistence.entity.security.Operation;
import com.example.persistence.entity.security.Role;
import com.example.persistence.repository.security.OperationRepository;
import com.example.persistence.repository.security.RoleRepository;
import com.example.persistence.repository.security.PermissionRepository;
import com.example.service.PermissionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PermissionServiceImpl implements PermissionService {
    private final OperationRepository operationRepository;
    private final RoleRepository roleRepository;
    private final PermissionRepository permissionRepository;

    public PermissionServiceImpl(OperationRepository operationRepository, RoleRepository roleRepository, PermissionRepository permissionRepository) {
        this.operationRepository = operationRepository;
        this.roleRepository = roleRepository;
        this.permissionRepository = permissionRepository;
    }

    @Override
    public Page<ShowPermission> findAll(Pageable pageable) {
        Page<GrantedPermission> entities=permissionRepository.findAll(pageable);
        if (entities.isEmpty()) throw  new ResourceNotFoundException("empty records");

        return entities.map(PermissionMapper::toGetShowDto);
    }

    @Override
    public ShowPermission findById(Integer permissionId) {
        return PermissionMapper.toGetShowDto(findByEntity(permissionId));
    }

    @Override
    public ShowPermission createOne(SavePermission savePermission) {
        Operation operation=operationRepository.findByName(savePermission.operation())
                .orElseThrow(() -> new ResourceNotFoundException("Operation not found. Operation: " + savePermission.operation()));
        Role role = roleRepository.findByName(savePermission.role())
                .orElseThrow(() -> new ResourceNotFoundException("Role not found. Role: " + savePermission.role()));
        GrantedPermission permissionCreated=permissionRepository.save(GrantedPermission.builder()
                        .role(role)
                        .operation(operation)
                .build());
        return PermissionMapper.toGetShowDto(permissionCreated);
    }
    @Override
    public void deleteOneById(Integer permissionId) {
        GrantedPermission permission = findByEntity(permissionId);
        permissionRepository.delete(permission);
    }

    private GrantedPermission findByEntity (Integer permissionId){
        return permissionRepository.findById(permissionId)
                .orElseThrow(()->new ResourceNotFoundException("Permission not found. Permission: " + permissionId));
    }
}
