package com.alwan.dansTest.jobsApps.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import java.io.Serializable;

@Entity
@Table(name = "users")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Users implements Serializable {

    private static final long serialVersionUID = -1466253324690025818L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(length = 50,name = "username",unique = true)
    private String username;

    @NotNull
    @Column(length = 100,name = "email",unique = true)
    private String email;

    @NotNull
    @Column(length = 200, name = "name")
    private String name;

    @NotNull
    @Column(name = "password")
    private String password;

    @NotNull
    @Column(length = 50,name = "roles")
    private String roles;

    @Column(name = "is_active")
    private Boolean isActive;

}
