package org.usuarios.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;


@Entity
@Data
@Table(name = "users")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "nombre")
    private String name;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Phones> phones;
    @Column(name = "created")
    private LocalDateTime created;
    @Column(name = "modified")
    private LocalDateTime modified;
    @Column(name = "lastlogin")
    private LocalDateTime lastLogin;
    @Column(name = "token")
    private String token;
    @Column(name = "active")
    private boolean isActive;



}
