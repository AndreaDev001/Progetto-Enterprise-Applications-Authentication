package com.enterpriseapplications.authserver.service.interfaces.users;

import com.enterpriseapplications.authserver.data.dto.input.CreateLocalUserDto;
import com.enterpriseapplications.authserver.data.dto.output.users.LocalUserDto;
import com.enterpriseapplications.authserver.data.entities.enums.Provider;

import java.util.UUID;

public interface LocalUserService {

    LocalUserDto getUser(UUID id);
    LocalUserDto getUser(String username);
    LocalUserDto createUser(CreateLocalUserDto createUserDto);
}
