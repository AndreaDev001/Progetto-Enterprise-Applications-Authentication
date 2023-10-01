package com.enterpriseapplications.authserver.data.dao.users;


import com.enterpriseapplications.authserver.data.entities.users.FacebookUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface FacebookUserDao extends JpaRepository<FacebookUser, UUID>
{
    @Query("select f from FacebookUser f where f.username = :requiredUsername")
    Optional<FacebookUser> getFacebookUser(@Param("requiredUsername") String username);
}
