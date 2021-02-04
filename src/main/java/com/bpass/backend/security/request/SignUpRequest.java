package com.bpass.backend.security.request;

import com.bpass.backend.security.model.UserRole;
import com.bpass.backend.security.model.dto.SignUpAdminDto;
import com.bpass.backend.security.model.dto.SignUpStoreDto;
import com.bpass.backend.security.model.dto.SignUpUserDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SignUpRequest {

    private String id;
    private String password;
    private String name;
    private String phoneNumber;
    private String address;

    private UserRole role;

    private String storePhoneNumber;
    private String storeName;

    public SignUpUserDto toUserDto() {
        return new SignUpUserDto(id,password,name,phoneNumber,address);
    }

    public SignUpStoreDto toStoreDto(){
        return new SignUpStoreDto(id,password,name,phoneNumber,address,storePhoneNumber,storeName);
    }

    public SignUpAdminDto toAdminDto() {
        return new SignUpAdminDto(id,password,name,phoneNumber,address);
    }
}
