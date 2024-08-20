package com.example.controller;

import com.example.dto.request.SavePermission;
import com.example.dto.response.ShowPermission;
import com.example.service.PermissionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/permissions")
public class PermissionController {
    private final PermissionService permissionService;

    public PermissionController(PermissionService permissionService) {
        this.permissionService = permissionService;
    }

    @GetMapping
    public ResponseEntity<Page<ShowPermission>> findAll (Pageable pageable){
        return ResponseEntity.ok(permissionService.findAll(pageable));
    }
    @GetMapping("/{permissionId}")
    public ResponseEntity<ShowPermission> findAll (@PathVariable Integer permissionId){
        return ResponseEntity.ok(permissionService.findById(permissionId));
    }
    @PostMapping
    public ResponseEntity<ShowPermission> createOne (@Valid @RequestBody SavePermission savePermission){
        return ResponseEntity.status(HttpStatus.CREATED).body(permissionService.createOne(savePermission));
    }
    @DeleteMapping("/{permissionId}")
    public ResponseEntity<Void> deleteOneById (@PathVariable Integer permissionId) {
        permissionService.deleteOneById(permissionId);
        return ResponseEntity.noContent().build();
    }

}
