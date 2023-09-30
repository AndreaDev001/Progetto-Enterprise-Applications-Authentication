package com.enterpriseapplications.authserver.service.implementations;


import com.enterpriseapplications.authserver.data.dao.RoleDao;
import com.enterpriseapplications.authserver.data.dao.UserDao;
import com.enterpriseapplications.authserver.data.dto.input.CreateUserDto;
import com.enterpriseapplications.authserver.data.dto.output.UserDto;
import com.enterpriseapplications.authserver.data.entities.Role;
import com.enterpriseapplications.authserver.data.entities.User;
import com.enterpriseapplications.authserver.service.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImp implements UserService {

    private final UserDao userDao;
    private final RoleDao roleDao;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDto getUserByID(UUID uuid) {
        User user = this.userDao.findById(uuid).orElseThrow();
        return this.modelMapper.map(user,UserDto.class);
    }

    @Override
    public UserDto getUserByUsername(String username) {
        User user = this.userDao.getUserByUsername(username).orElseThrow();
        return this.modelMapper.map(user,UserDto.class);
    }

    @Override
    @Transactional
    public UserDto createUser(CreateUserDto createUserDto) {
        User user = new User();
        user.setEmail(createUserDto.getEmail());
        user.setUsername(createUserDto.getUsername());
        user.setPassword(passwordEncoder.encode(createUserDto.getPassword()));
        Set<Role> roles = new HashSet<>();
        createUserDto.getRoles().forEach(role -> {
            Role requiredRole = this.roleDao.getRoleByName(role).orElseThrow();
            roles.add(requiredRole);
        });
        user.setRoles(roles);
        this.userDao.save(user);
        return this.modelMapper.map(user,UserDto.class);
    }
}
