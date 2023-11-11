package com.enterpriseapplications.authserver.service.implementations.users;


import com.enterpriseapplications.authserver.data.dao.users.UserDao;
import com.enterpriseapplications.authserver.data.dto.output.users.UserDto;
import com.enterpriseapplications.authserver.service.interfaces.users.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImp implements UserService
{
    private final UserDao userDao;
    private final ModelMapper modelMapper;

    @Override
    public UserDto getUserByID(UUID uuid) {
        return this.modelMapper.map(this.userDao.findById(uuid).orElseThrow(),UserDto.class);
    }

    @Override
    public UserDto getUserByUsername(String username) {
        return this.modelMapper.map(this.userDao.getUserByUsername(username).orElseThrow(),UserDto.class);
    }
}
