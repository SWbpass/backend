package com.bpass.backend.security.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SignUpUserDto {
  private String id;
  private String password;
  private String name;
  private String phoneNumber;
  private String address;
}
