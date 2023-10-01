package com.enterpriseapplications.authserver.service.interfaces.users;

import com.enterpriseapplications.authserver.data.dto.output.users.FacebookUserDto;
import com.enterpriseapplications.authserver.data.entities.users.FacebookUser;

import java.util.UUID;

public interface FacebookUserService
{
    FacebookUserDto getUser(UUID id);
    FacebookUserDto getUser(String username);
}
