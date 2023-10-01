package com.enterpriseapplications.authserver;

import com.enterpriseapplications.authserver.data.dao.users.UserDao;
import com.enterpriseapplications.authserver.data.entities.users.LocalUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PasswordAuthenticationProvider implements AuthenticationProvider
{
    private final UserDao userDao;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        LocalUser requiredUser = this.userDao.getUserByUsername(authentication.getName()).orElseThrow();
        if(passwordEncoder.matches(authentication.getCredentials().toString(),requiredUser.getPassword())) {
            return new UsernamePasswordAuthenticationToken(requiredUser.getId(),authentication.getCredentials(),requiredUser.getAuthorities());
        }
        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
