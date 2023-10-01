package com.enterpriseapplications.authserver.service.implementations.users;


import com.enterpriseapplications.authserver.data.dao.users.GithubUserDao;
import com.enterpriseapplications.authserver.data.dto.output.users.GithubUserDto;
import com.enterpriseapplications.authserver.data.entities.users.GithubUser;
import com.enterpriseapplications.authserver.service.interfaces.users.GithubUserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GithubUserServiceImp implements GithubUserService
{
    private final GithubUserDao githubUserDao;
    private final ModelMapper modelMapper;

    @Override
    public GithubUserDto getUser(UUID id) {
        return this.modelMapper.map(this.githubUserDao.findById(id).orElseThrow(), GithubUserDto.class);
    }

    @Override
    public GithubUserDto getUser(String username) {
        return this.modelMapper.map(this.githubUserDao.getGithubUserBy(username).orElseThrow(), GithubUserDto.class);
    }
}
