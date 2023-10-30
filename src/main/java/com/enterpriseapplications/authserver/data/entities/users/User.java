package com.enterpriseapplications.authserver.data.entities.users;

import com.enterpriseapplications.authserver.data.entities.Role;
import com.enterpriseapplications.authserver.data.entities.enums.Provider;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "USERS")
@Inheritance(strategy = InheritanceType.JOINED)
@EntityListeners({AuditingEntityListener.class})
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User
{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "USER_ID")
    protected UUID id;

    @Column(name = "EMAIL",unique = true)
    protected String email;

    @Column(name = "USERNAME",unique = false)
    protected String username;

    @Column(name = "PROVIDER",unique = false)
    @Enumerated(EnumType.STRING)
    protected Provider provider;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "USER_ROLE",joinColumns = @JoinColumn(name = "USER_ID"),inverseJoinColumns = @JoinColumn(name = "ROLE_ID"))
    private Set<Role> roles = new HashSet<>();

    @CreatedDate
    @Column(name = "CREATED_DATE",unique = false)
    protected LocalDate createdDate;

    @LastModifiedDate
    @Column(name = "LAST_MODIFIED_DATE",unique = false)
    protected LocalDate lastModifiedDate;

}
