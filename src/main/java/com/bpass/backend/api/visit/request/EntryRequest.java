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
public class EntryRequest {
  private String storeId;
  private String visitorId;
  private LocalDateTime entryTime;
}
