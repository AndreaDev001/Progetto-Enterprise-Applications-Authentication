package com.enterpriseapplications.authserver.service.implementations.users;


import com.enterpriseapplications.authserver.data.dao.RoleDao;
import com.enterpriseapplications.authserver.data.dao.users.LocalUserDao;
import com.enterpriseapplications.authserver.data.dao.users.UserDao;
import com.enterpriseapplications.authserver.data.dto.input.CreateLocalUserDto;
import com.enterpriseapplications.authserver.data.dto.output.users.LocalUserDto;
import com.enterpriseapplications.authserver.data.entities.Role;
import com.enterpriseapplications.authserver.data.entities.enums.Provider;
import com.enterpriseapplications.authserver.data.entities.users.LocalUser;
import com.enterpriseapplications.authserver.service.interfaces.users.LocalUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class LocalUserServiceImp implements LocalUserService {

    private final LocalUserDao localUserDao;
    private final RoleDao roleDao;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public LocalUserDto getUser(UUID id) {
        return this.modelMapper.map(this.localUserDao.findById(id).orElseThrow(),LocalUserDto.class);
    }

    @Override
    public LocalUserDto getUser(String username) {
        return this.modelMapper.map(this.localUserDao.getUserByUsername(username),LocalUserDto.class);
    }

    @Override
    @Transactional
    public LocalUserDto createUser(CreateLocalUserDto createUserDto) {
        LocalUser user = new LocalUser();
        user.setEmail(createUserDto.getEmail());
        user.setUsername(createUserDto.getUsername());
        user.setPassword(passwordEncoder.encode(createUserDto.getPassword()));
        Set<Role> roles = new HashSet<>();
        createUserDto.getRoles().forEach(role -> {
            Role requiredRole = this.roleDao.getRoleByName(role).orElseThrow();
            roles.add(requiredRole);
        });
        user.setRoles(roles);
        user.setProvider(Provider.LOCAL);
        this.localUserDao.save(user);
        return this.modelMapper.map(user, LocalUserDto.class);
    }
}
