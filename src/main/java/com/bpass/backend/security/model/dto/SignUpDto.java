package com.bpass.backend.security.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SignUpDto {
    private String accessToken;
    private Long id;
}
