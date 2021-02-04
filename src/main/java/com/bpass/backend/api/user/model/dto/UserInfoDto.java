package com.bpass.backend.api.user.model.dto;

import com.bpass.backend.api.user.model.Users;
import lombok.Getter;

@Getter
public class UserInfoDto {
    private long id;
    private String name;
    private String phoneNumber;
    private String address;

    public UserInfoDto(Users user) {
        this.id = user.getId();
        this.name = user.getName();
        this.phoneNumber = user.getPhoneNumber();
        this.address = user.getAddress();
    }
}
