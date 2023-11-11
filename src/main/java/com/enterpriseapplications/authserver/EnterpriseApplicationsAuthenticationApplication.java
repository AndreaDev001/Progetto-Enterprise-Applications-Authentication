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

        //Commentare se non si crea il database
        Client client = new Client();
        Role firstRole = new Role();
        Role secondRole = new Role();
        firstRole.setName("ROLE_USER");
        secondRole.setName("ROLE_ADMIN");
        client.setClientID("client");
        client.setClientSecret(passwordEncoder.encode("secret"));
        client.setAuthenticationMethods(Set.of(ClientAuthenticationMethod.CLIENT_SECRET_BASIC));
        client.setAuthorizationGrantTypes(Set.of(AuthorizationGrantType.AUTHORIZATION_CODE,AuthorizationGrantType.CLIENT_CREDENTIALS,AuthorizationGrantType.REFRESH_TOKEN));
        client.setRedirectUris(Set.of("https://oauthdebugger.com/debug","https://oauth.pstmn.io/v1/callback","clowning://moose.ac"));
        client.setScopes(Set.of("openid"));
        client.setProofKey(true);
        LocalUser firstUser = new LocalUser();
        firstUser.setEmail("marchioandrea02@gmail.com");
        firstUser.setUsername("andrea");
        firstUser.setName("Andrea");
        firstUser.setSurname("Marchio");
        firstUser.setPassword(passwordEncoder.encode("password"));
        firstUser.setRoles(Set.of(firstRole,secondRole));
        firstUser.setProvider(Provider.LOCAL);
        LocalUser secondUser = new LocalUser();
        secondUser.setEmail("andrea.marchio@virgilio.it");
        secondUser.setUsername("andrea1");
        secondUser.setName("Andrea");
        secondUser.setSurname("Marchio");
        secondUser.setPassword(passwordEncoder.encode("password"));
        secondUser.setRoles(Set.of(firstRole));
        secondUser.setProvider(Provider.LOCAL);
        LocalUser thirdUser = new LocalUser();
        thirdUser.setEmail("andrea.marchio01@virgilio.it");
        thirdUser.setUsername("andreamarchio01");
        thirdUser.setName("andrea");
        thirdUser.setSurname("marchio");
        thirdUser.setPassword(passwordEncoder.encode("password"));
        thirdUser.setRoles(Set.of(firstRole));
        thirdUser.setProvider(Provider.LOCAL);
        LocalUser fourthUser = new LocalUser();
        fourthUser.setEmail("andrea.marchio02@virgilio.it");
        fourthUser.setUsername("andrea01");
        fourthUser.setName("Andrea");
        fourthUser.setSurname("Marchio");
        fourthUser.setPassword(passwordEncoder.encode("password"));
        fourthUser.setRoles(Set.of(firstRole));
        fourthUser.setProvider(Provider.LOCAL);
        this.roleDao.save(firstRole);
        this.roleDao.save(secondRole);
        this.clientDao.save(client);
        this.localUserDao.save(firstUser);
        this.localUserDao.save(secondUser);
        this.localUserDao.save(thirdUser);
        this.localUserDao.save(fourthUser);
    }
}
