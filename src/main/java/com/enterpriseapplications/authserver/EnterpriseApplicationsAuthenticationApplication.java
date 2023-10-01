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
public class EnterpriseApplicationsAuthenticationApplication {


    public static void main(String[] args) {
        SpringApplication.run(EnterpriseApplicationsAuthenticationApplication.class, args);
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
