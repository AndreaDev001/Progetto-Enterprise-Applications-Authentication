package com.enterpriseapplications.authserver.data.dao.users;


import com.enterpriseapplications.authserver.data.entities.users.LocalUser;
import com.enterpriseapplications.authserver.data.entities.users.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;
import java.util.Optional;

@Repository
public interface UserDao extends JpaRepository<User, UUID>
{
    @Query("select u from LocalUser u where u.username = :requiredUsername")
    Optional<LocalUser> getUserByUsername(@Param("requiredUsername") String username);
}
