package com.enterpriseapplications.authserver;

import com.enterpriseapplications.authserver.data.dao.ClientDao;
import com.enterpriseapplications.authserver.data.dao.RoleDao;
import com.enterpriseapplications.authserver.data.entities.Client;
import com.enterpriseapplications.authserver.data.entities.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;

import java.util.List;
import java.util.Set;

@SpringBootApplication
public class EnterpriseApplicationsAuthenticationApplication implements CommandLineRunner{

    @Autowired
    private RoleDao roleDao;
    @Autowired
    private ClientDao clientDao;

    public static void main(String[] args) {
        SpringApplication.run(EnterpriseApplicationsAuthenticationApplication.class, args);
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void run(String... args) throws Exception {
        Client client = new Client();
        Role firstRole = new Role();
        Role secondRole = new Role();
        firstRole.setName("ROLE_USER");
        secondRole.setName("ROLE_ADMIN");
        client.setClientID("client");
        client.setClientSecret("secret");
        client.setAuthenticationMethods(Set.of(ClientAuthenticationMethod.CLIENT_SECRET_BASIC));
        client.setAuthorizationGrantTypes(Set.of(AuthorizationGrantType.AUTHORIZATION_CODE,AuthorizationGrantType.CLIENT_CREDENTIALS,AuthorizationGrantType.REFRESH_TOKEN));
        client.setRedirectUris(Set.of("https://oauthdebugger.com/debug","https://oauth.pstmn.io/v1/callback"));
        client.setScopes(Set.of("openid"));
        this.roleDao.save(firstRole);
        this.roleDao.save(secondRole);
        this.clientDao.save(client);
    }
}
