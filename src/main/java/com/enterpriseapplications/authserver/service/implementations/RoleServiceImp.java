package com.enterpriseapplications.authserver.service.implementations;

import com.enterpriseapplications.authserver.data.dao.RoleDao;
import com.enterpriseapplications.authserver.data.dto.output.RoleDto;
import com.enterpriseapplications.authserver.data.entities.Role;
import com.enterpriseapplications.authserver.service.interfaces.RoleService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RoleServiceImp implements RoleService {

    private final RoleDao roleDao;
    private final ModelMapper modelMapper;

    @Override
    public RoleDto getRoleByID(UUID id) {
        Role role = this.roleDao.findById(id).orElseThrow();
        return this.modelMapper.map(role,RoleDto.class);
    }

    @Override
    public RoleDto getRoleByName(String name) {
        Role role = this.roleDao.getRoleByName(name).orElseThrow();
        return this.modelMapper.map(role,RoleDto.class);
    }
}
