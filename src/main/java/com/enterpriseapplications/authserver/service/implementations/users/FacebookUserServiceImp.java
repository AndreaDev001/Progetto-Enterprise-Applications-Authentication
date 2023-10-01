package com.enterpriseapplications.authserver.service.implementations.users;

import com.enterpriseapplications.authserver.data.dao.users.FacebookUserDao;
import com.enterpriseapplications.authserver.data.dto.output.users.FacebookUserDto;
import com.enterpriseapplications.authserver.data.entities.users.FacebookUser;
import com.enterpriseapplications.authserver.service.interfaces.users.FacebookUserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FacebookUserServiceImp implements FacebookUserService
{
    private final FacebookUserDao facebookUserDao;
    private final ModelMapper modelMapper;

    @Override
    public FacebookUserDto getUser(UUID id) {
        return this.modelMapper.map(this.facebookUserDao.findById(id).orElseThrow(), FacebookUserDto.class);
    }

    @Override
    public FacebookUserDto getUser(String username) {
        return this.modelMapper.map(this.facebookUserDao.getFacebookUser(username),FacebookUserDto.class);
    }
}
