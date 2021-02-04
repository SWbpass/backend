package com.bpass.backend.fcm.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@NoArgsConstructor
public class FcmTokens {
    @Id
    private long id;
    private String token;
}
