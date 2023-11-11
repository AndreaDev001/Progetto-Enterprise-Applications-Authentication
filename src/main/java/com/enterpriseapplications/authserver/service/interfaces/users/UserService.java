package com.enterpriseapplications.authserver.service.interfaces.users;

import com.enterpriseapplications.authserver.data.dto.output.users.LocalUserDto;
import com.enterpriseapplications.authserver.data.dto.output.users.UserDto;

import java.util.UUID;

public interface UserService
{
    UserDto getUserByID(UUID uuid);
    UserDto getUserByUsername(String username);
}
