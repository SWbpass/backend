package com.bpass.backend.fcm.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PushContentsDto {
  String title;
  String body;
}
