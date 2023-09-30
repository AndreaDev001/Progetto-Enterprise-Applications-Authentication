package com.enterpriseapplications.authserver;

import com.enterpriseapplications.authserver.data.dao.RoleDao;
import com.enterpriseapplications.authserver.data.entities.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class EnterpriseApplicationsAuthenticationApplication implements CommandLineRunner {

    @Autowired
    private RoleDao roleDao;

    public static void main(String[] args) {
        SpringApplication.run(EnterpriseApplicationsAuthenticationApplication.class, args);
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void run(String... args) throws Exception {
        Role adminRole = new Role();
        Role userRole = new Role();
        adminRole.setName("ROLE_ADMIN");
        userRole.setName("ROLE_USER");
        this.roleDao.save(adminRole);
        this.roleDao.save(userRole);
    }
}
