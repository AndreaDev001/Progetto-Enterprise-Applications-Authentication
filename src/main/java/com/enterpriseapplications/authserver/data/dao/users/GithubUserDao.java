package com.enterpriseapplications.authserver.data.dao.users;


import com.enterpriseapplications.authserver.data.entities.users.GithubUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;
import java.util.Optional;

@Repository
public interface GithubUserDao extends JpaRepository<GithubUser, UUID>
{
    @Query("select g from GithubUser g where g.username = :requiredUsername")
    Optional<GithubUser> getGithubUserBy(@Param("requiredUsername") String username);
}
