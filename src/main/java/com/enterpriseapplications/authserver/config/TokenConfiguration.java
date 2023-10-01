package com.enterpriseapplications.authserver.config;


import com.enterpriseapplications.authserver.data.dao.users.FacebookUserDao;
import com.enterpriseapplications.authserver.data.dao.users.GithubUserDao;
import com.enterpriseapplications.authserver.data.dao.users.GoogleUserDao;
import com.enterpriseapplications.authserver.data.dao.users.LocalUserDao;
import com.enterpriseapplications.authserver.data.entities.Role;
import com.enterpriseapplications.authserver.data.entities.users.GoogleUser;
import com.enterpriseapplications.authserver.data.entities.users.LocalUser;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.server.authorization.token.JwtEncodingContext;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenCustomizer;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Configuration
@RequiredArgsConstructor
public class TokenConfiguration
{
    private final LocalUserDao localUserDao;
    private final GoogleUserDao googleUserDao;
    private final GithubUserDao githubUserDao;
    private final FacebookUserDao facebookUserDao;

    @Bean
    public OAuth2TokenCustomizer<JwtEncodingContext> tokenCustomizer() {
        return context -> {
            Authentication principal = context.getPrincipal();
            context.getClaims().claim("type",context.getTokenType().getValue());
            Set<String> roles = new HashSet<>();
            String provider = "LOCAL";
            if(principal instanceof UsernamePasswordAuthenticationToken) {
                LocalUser requiredUser = this.localUserDao.findById(UUID.fromString(principal.getName())).orElseThrow();
                provider = requiredUser.getProvider().toString();
                roles = principal.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toSet());
                context.getClaims().claim("username",requiredUser.getUsername());
                context.getClaims().claim("email",requiredUser.getEmail());
            }
            else if(principal instanceof OAuth2AuthenticationToken authenticationToken) {
                GoogleUser googleUser = this.googleUserDao.getGoogleUserByExternalID(authenticationToken.getPrincipal().getAttribute("sub")).orElseThrow();
                for(Role role : googleUser.getRoles()) {
                    String name = role.getName();
                    roles.add(name);
                }
                provider = googleUser.getProvider().toString();
                context.getClaims().claim("sub",googleUser.getId().toString());
                context.getClaims().claim("name",googleUser.getGivenName());
                context.getClaims().claim("surname",googleUser.getFamilyName());
                context.getClaims().claim("email",googleUser.getEmail());
                context.getClaims().claim("username",googleUser.getUsername());
            }
            context.getClaims().claim("provider",provider);
            context.getClaims().claim("roles",roles);
        };
    }
}
