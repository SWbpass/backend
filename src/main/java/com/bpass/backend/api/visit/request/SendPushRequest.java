package com.bpass.backend.api.visit.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SendPushRequest {
  private Long storeId;
  private Long visitorId;
  private LocalDateTime entryTime;
  private LocalDateTime exitTime;
}
