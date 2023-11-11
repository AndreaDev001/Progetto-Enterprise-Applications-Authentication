package com.enterpriseapplications.authserver.service.implementations.users;


import com.enterpriseapplications.authserver.data.dao.users.GoogleUserDao;
import com.enterpriseapplications.authserver.data.dto.output.users.GoogleUserDto;
import com.enterpriseapplications.authserver.service.interfaces.users.GoogleUserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GoogleUserServiceImp implements GoogleUserService
{
    private final GoogleUserDao googleUserDao;
    private final ModelMapper modelMapper;

    @Override
    public GoogleUserDto getUser(UUID uuid) {
        return this.modelMapper.map(this.googleUserDao.findById(uuid).orElseThrow(),GoogleUserDto.class);
    }

    @Override
    public GoogleUserDto getUser(String username) {
        return this.modelMapper.map(this.googleUserDao.getGoogleUser(username).orElseThrow(),GoogleUserDto.class);
    }
}
