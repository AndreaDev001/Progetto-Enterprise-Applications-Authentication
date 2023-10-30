package com.enterpriseapplications.authserver;

import com.enterpriseapplications.authserver.data.dao.ClientDao;
import com.enterpriseapplications.authserver.data.dao.RoleDao;
import com.enterpriseapplications.authserver.data.dao.users.LocalUserDao;
import com.enterpriseapplications.authserver.data.entities.Client;
import com.enterpriseapplications.authserver.data.entities.Role;
import com.enterpriseapplications.authserver.data.entities.enums.Provider;
import com.enterpriseapplications.authserver.data.entities.users.LocalUser;
import com.enterpriseapplications.authserver.data.entities.users.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;

import java.util.List;
import java.util.Set;

@SpringBootApplication
public class EnterpriseApplicationsAuthenticationApplication implements CommandLineRunner {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private LocalUserDao localUserDao;
    @Autowired
    private RoleDao roleDao;
    @Autowired
    private ClientDao clientDao;

    public static void main(String[] args) {
        SpringApplication.run(EnterpriseApplicationsAuthenticationApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        /**Client client = new Client();
        Role firstRole = new Role();
        Role secondRole = new Role();
        firstRole.setName("ROLE_USER");
        secondRole.setName("ROLE_ADMIN");
        client.setClientID("client");
        client.setClientSecret(passwordEncoder.encode("secret"));
        client.setAuthenticationMethods(Set.of(ClientAuthenticationMethod.CLIENT_SECRET_BASIC));
        client.setAuthorizationGrantTypes(Set.of(AuthorizationGrantType.AUTHORIZATION_CODE,AuthorizationGrantType.CLIENT_CREDENTIALS,AuthorizationGrantType.REFRESH_TOKEN));
        client.setRedirectUris(Set.of("https://oauthdebugger.com/debug","https://oauth.pstmn.io/v1/callback"));
        client.setScopes(Set.of("openid"));
        client.setProofKey(true);
        LocalUser localUser = new LocalUser();
        localUser.setEmail("marchioandrea02@gmail.com");
        localUser.setUsername("andrea");
        localUser.setName("Andrea");
        localUser.setSurname("Marchio");
        localUser.setPassword(passwordEncoder.encode("password"));
        localUser.setRoles(Set.of(firstRole,secondRole));
        localUser.setProvider(Provider.LOCAL);
        this.roleDao.save(firstRole);
        this.roleDao.save(secondRole);
        this.clientDao.save(client);
        this.localUserDao.save(localUser);**/
    }
}
