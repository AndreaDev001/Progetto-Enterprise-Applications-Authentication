package com.enterpriseapplications.authserver.controllers.users;


import com.enterpriseapplications.authserver.service.interfaces.users.FacebookUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users/facebook")
@RequiredArgsConstructor
public class FacebookUserController
{
    private final FacebookUserService facebookUserService;
}
