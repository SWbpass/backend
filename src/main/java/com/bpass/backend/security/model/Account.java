package com.bpass.backend.security.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Getter
@NoArgsConstructor
@MappedSuperclass
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true)
    private String userId;

    private String password;

    private String name;

    private UserStatus state;

    private String phoneNumber;

    private String address;

    @ElementCollection(fetch = FetchType.LAZY)
    private List<UserRole> roles;

    public Account(String userId, String password, String name, UserStatus state, String phoneNumber, String address, List<UserRole> roles) {
        this.userId = userId;
        this.password = password;
        this.name = name;
        this.state = state;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.roles = roles;
    }
}
