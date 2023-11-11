package com.enterpriseapplications.authserver.data.dao.users;


import com.enterpriseapplications.authserver.data.entities.users.LocalUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface LocalUserDao extends JpaRepository<LocalUser, UUID>
{
    @Query("select u from LocalUser u where u.username = :requiredUsername")
    Optional<LocalUser> getUserByUsername(@Param("requiredUsername") String username);
}
