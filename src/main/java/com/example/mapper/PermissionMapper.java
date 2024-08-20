package com.example.mapper;

import com.example.dto.request.SavePermission;
import com.example.dto.response.ShowPermission;
import com.example.persistence.entity.security.GrantedPermission;
import com.example.persistence.entity.security.Operation;
import com.example.persistence.entity.security.Role;


public  class PermissionMapper {
    public static  ShowPermission toGetShowDto (GrantedPermission entity){
        if (entity==null)return null;
        return new ShowPermission(
                entity.getId(),
                entity.getOperation().getName(),
                entity.getOperation().getHttpMethod(),
                entity.getOperation().getModule().getName(),
                entity.getRole().getName()
        );
    }
    public static GrantedPermission toGetEntity (SavePermission savePermission,
                                                 Role role,
                                                 Operation operation){
        if (savePermission==null) return null;
        return GrantedPermission.builder()
                .build();
    }



}
