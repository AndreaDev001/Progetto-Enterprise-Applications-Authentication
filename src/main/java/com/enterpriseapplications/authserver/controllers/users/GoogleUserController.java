package com.enterpriseapplications.authserver.controllers.users;


import com.enterpriseapplications.authserver.service.interfaces.users.GoogleUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users/google")
@RequiredArgsConstructor
public class GoogleUserController
{
    private final GoogleUserService googleUserService;
}
