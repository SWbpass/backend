package com.bpass.backend.api.user.model;

import com.bpass.backend.security.model.Account;
import com.bpass.backend.security.model.UserRole;
import com.bpass.backend.security.model.UserStatus;

import java.util.List;
import javax.persistence.Entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Users extends Account {

    public Users(String userId, String password, String name, UserStatus state, String phoneNumber, String address, List<UserRole> roles) {
        super(userId, password, name, state, phoneNumber, address, roles);
    }
}
