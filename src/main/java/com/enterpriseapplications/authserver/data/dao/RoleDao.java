package com.enterpriseapplications.authserver.data.dao;

import com.enterpriseapplications.authserver.data.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface RoleDao extends JpaRepository<Role,UUID>
{
    @Query("select r from Role r where r.name = :requiredName")
    Optional<Role> getRoleByName(@Param("requiredName") String requiredName);
}
