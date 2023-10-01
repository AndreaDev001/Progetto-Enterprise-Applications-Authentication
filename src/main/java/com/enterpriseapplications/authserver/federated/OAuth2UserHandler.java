package com.enterpriseapplications.authserver.federated;

import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class OAuth2UserHandler implements Consumer<OAuth2User> {

    private final UserRepository userRepository = new UserRepository();


    @Override
    public void accept(OAuth2User user) {
        if (this.userRepository.findByName(user.getName()) == null) {
            this.userRepository.save(user);
        }
    }
    static class UserRepository {
        private final Map<String,OAuth2User> userCache = new HashMap<>();

        public OAuth2User findByName(String name) {
            return this.userCache.get(name);
        }
        public void save(OAuth2User oAuth2User) {
            this.userCache.put(oAuth2User.getName(),oAuth2User);
        }
    }
}
