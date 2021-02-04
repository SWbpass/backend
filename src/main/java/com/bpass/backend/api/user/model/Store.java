package com.bpass.backend.api.user.model;

import com.bpass.backend.security.model.Account;
import com.bpass.backend.security.model.UserRole;
import com.bpass.backend.security.model.UserStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Store extends Account {

    private String storePhoneNumber;
    private String storeName;

    public Store(String userId, String password, String name, UserStatus state, String phoneNumber, String address, String storePhoneNumber, String storeName, List<UserRole> roles) {
        super(userId, password, name, state, phoneNumber, address, roles);
        this.storePhoneNumber = storePhoneNumber;
        this.storeName = storeName;
    }
}
