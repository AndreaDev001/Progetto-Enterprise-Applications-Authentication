package com.enterpriseapplications.authserver.config;

import com.enterpriseapplications.authserver.PasswordAuthenticationProvider;
import com.enterpriseapplications.authserver.data.dao.users.LocalUserDao;
import com.enterpriseapplications.authserver.data.dao.users.UserDao;
import com.enterpriseapplications.authserver.data.entities.users.LocalUser;
import com.enterpriseapplications.authserver.federated.FederatedIdentityConfigurer;
import com.enterpriseapplications.authserver.federated.OAuth2UserHandler;
import com.enterpriseapplications.authserver.service.implementations.UserDetailsServiceImp;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.server.authorization.*;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OAuth2AuthorizationServerConfigurer;
import org.springframework.security.oauth2.server.authorization.settings.AuthorizationServerSettings;
import org.springframework.security.oauth2.server.authorization.token.JwtEncodingContext;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenCustomizer;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.session.HttpSessionEventPublisher;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.Set;

@Configuration
@RequiredArgsConstructor
public class AuthConfig
{
    private final UserDetailsServiceImp userDetailsServiceImp;
    private final PasswordAuthenticationProvider passwordAuthenticationProvider;
    private final OAuth2UserHandler oAuth2UserHandler;

    @Bean
    @Order(1)
    public SecurityFilterChain authFilterChain(HttpSecurity httpSecurity) throws Exception {
        OAuth2AuthorizationServerConfiguration.applyDefaultSecurity(httpSecurity);
        httpSecurity.getConfigurer(OAuth2AuthorizationServerConfigurer.class)
                .oidc(Customizer.withDefaults());
        httpSecurity.oauth2ResourceServer(oauth2ResourceServer -> oauth2ResourceServer.jwt(Customizer.withDefaults()));
        httpSecurity.apply(new FederatedIdentityConfigurer());
        return httpSecurity.build();
    }

    @Bean
    @Order(2)
    public SecurityFilterChain webFilterChain(HttpSecurity httpSecurity) throws Exception {
        FederatedIdentityConfigurer federatedIdentityConfigurer = new FederatedIdentityConfigurer()
                .oAuth2UserConsumer(oAuth2UserHandler);
        httpSecurity.authorizeHttpRequests(auth ->
                        auth.requestMatchers("/users/**","/localUsers/**","/googleUsers/**","/githubUsers/**","/facebookUsers/**").permitAll()
                                .requestMatchers("/roles/**").permitAll()
                                .requestMatchers("/clients/**").permitAll()
                                .requestMatchers("/login").permitAll()
                                .requestMatchers("/register").permitAll()
                                .requestMatchers("/documentation/**").permitAll()
                                .anyRequest().authenticated())
                .formLogin(Customizer.withDefaults());
        httpSecurity.csrf(httpSecurityCsrfConfigurer -> httpSecurityCsrfConfigurer.ignoringRequestMatchers("/users/**","/localUsers/**","/clients/**"));
        httpSecurity.apply(federatedIdentityConfigurer);
        return httpSecurity.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return userDetailsServiceImp;
    }

    @Bean
    public AuthorizationServerSettings authorizationServerSettings() {
        return AuthorizationServerSettings.builder().issuer("http://localhost:9000").build();
    }
    @Bean
    public JwtDecoder jwtDecoder(JWKSource<SecurityContext> jwkSource) {
        return OAuth2AuthorizationServerConfiguration.jwtDecoder(jwkSource);
    }

    @Bean
    public JWKSource<SecurityContext> jwkSource() {
        RSAKey rsaKey = generateRSAKey();
        JWKSet jwkSet = new JWKSet(rsaKey);
        return ((jwkSelector, securityContext) -> jwkSelector.select(jwkSet));
    }

    private RSAKey generateRSAKey() {
        KeyPair keyPair = generateKeyPair();
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
        return new RSAKey.Builder(publicKey).privateKey(privateKey).keyID(UUID.randomUUID().toString()).build();
    }

    private KeyPair generateKeyPair() {
        KeyPair keyPair = null;
        try
        {
            KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
            keyPair = generator.generateKeyPair();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        return keyPair;
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity httpSecurity) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = httpSecurity.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.authenticationProvider(passwordAuthenticationProvider);
        return authenticationManagerBuilder.build();
    }

    @Bean
    public SessionRegistry sessionRegistry() {
        return new SessionRegistryImpl();
    }

    @Bean
    public HttpSessionEventPublisher httpSessionEventPublisher() {
        return new HttpSessionEventPublisher();
    }

    @Bean
    public OAuth2AuthorizationService authorizationService() {
        return new InMemoryOAuth2AuthorizationService();
    }

    @Bean
    public OAuth2AuthorizationConsentService auth2AuthorizationConsentService() {
        return new InMemoryOAuth2AuthorizationConsentService();
    }
}
