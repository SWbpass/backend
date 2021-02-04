package com.bpass.backend.security.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SignUpStoreDto {
  private String id;
  private String password;
  private String name;
  private String phoneNumber;
  private String address;
  private String storePhoneNumber;
  private String storeName;
  private String latitude;
  private String longitude;
}
