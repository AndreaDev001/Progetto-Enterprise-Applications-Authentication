package com.enterpriseapplications.authserver.service.interfaces.users;

import com.enterpriseapplications.authserver.data.dto.output.users.GithubUserDto;
import com.enterpriseapplications.authserver.data.entities.users.GithubUser;

import java.util.UUID;

public interface GithubUserService
{
    GithubUserDto getUser(UUID id);
    GithubUserDto getUser(String username);
}
