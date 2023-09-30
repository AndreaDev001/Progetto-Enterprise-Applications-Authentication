package com.enterpriseapplications.authserver.service.interfaces;

import com.enterpriseapplications.authserver.data.dto.input.CreateUserDto;
import com.enterpriseapplications.authserver.data.dto.output.UserDto;

import java.util.Optional;
import java.util.UUID;

public interface UserService {
    UserDto getUserByID(UUID uuid);
    UserDto getUserByUsername(String username);
    UserDto createUser(CreateUserDto createUserDto);
}
