package com.enterpriseapplications.authserver.service.interfaces;

import com.enterpriseapplications.authserver.data.dto.output.RoleDto;

import java.util.UUID;

public interface RoleService {
    RoleDto getRoleByID(UUID id);
    RoleDto getRoleByName(String name);
}
