package com.enterpriseapplications.authserver.service.interfaces.users;

import com.enterpriseapplications.authserver.data.dto.output.users.GoogleUserDto;

import java.util.UUID;

public interface GoogleUserService
{
    GoogleUserDto getUser(UUID uuid);
    GoogleUserDto getUser(String username);
}
