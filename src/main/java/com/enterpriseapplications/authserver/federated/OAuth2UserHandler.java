package com.enterpriseapplications.authserver.federated;

import com.enterpriseapplications.authserver.data.dao.RoleDao;
import com.enterpriseapplications.authserver.data.dao.users.GoogleUserDao;
import com.enterpriseapplications.authserver.data.entities.Role;
import com.enterpriseapplications.authserver.data.entities.users.GoogleUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.function.Consumer;


@Component
@RequiredArgsConstructor
public class OAuth2UserHandler implements Consumer<OAuth2User> {

    private final GoogleUserDao googleUserDao;
    private final RoleDao roleDao;

    @Override
    public void accept(OAuth2User user) {
        Role userRole = this.roleDao.getRoleByName("ROLE_USER").orElseThrow();
        String sub = (String)user.getAttribute("sub");
        Optional<GoogleUser> googleUserOptional = this.googleUserDao.getGoogleUserByExternalID(sub);
        if(googleUserOptional.isEmpty()) {
            GoogleUser googleUser = GoogleUser.fromOAuth2User(user);
            googleUser.setRoles(Set.of(userRole));
            this.googleUserDao.save(googleUser);
        }
    }
}
