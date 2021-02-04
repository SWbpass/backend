package com.bpass.backend.security.request;

import com.bpass.backend.security.model.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SignInRequest {
  private String id;
  private String password;
  private UserRole role;
}
