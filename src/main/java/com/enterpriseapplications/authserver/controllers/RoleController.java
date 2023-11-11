package com.enterpriseapplications.authserver.controllers;

import com.enterpriseapplications.authserver.data.dto.output.RoleDto;
import com.enterpriseapplications.authserver.service.interfaces.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/roles")
@RequiredArgsConstructor
public class RoleController {

    private final RoleService roleService;

    @GetMapping("/private/id/{roleID}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<RoleDto> getRole(@PathVariable("roleID")UUID roleID) {
        return ResponseEntity.ok(this.roleService.getRoleByID(roleID));
    }
    @GetMapping("/private/name/{roleName}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<RoleDto> getRole(@PathVariable("roleName") String roleName) {
        return ResponseEntity.ok(this.roleService.getRoleByName(roleName));
    }
}
