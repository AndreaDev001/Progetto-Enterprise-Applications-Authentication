package com.enterpriseapplications.authserver.data.dao.users;


import com.enterpriseapplications.authserver.data.entities.users.GoogleUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;
import java.util.Optional;

@Repository
public interface GoogleUserDao extends JpaRepository<GoogleUser, UUID>
{
    @Query("select g from GoogleUser g where g.username = :requiredUsername")
    Optional<GoogleUser> getGoogleUser(@Param("requiredUsername") String username);
}
