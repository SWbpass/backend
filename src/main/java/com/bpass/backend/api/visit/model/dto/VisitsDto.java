package com.bpass.backend.api.visit.model.dto;

import com.bpass.backend.api.user.model.dto.StoreInfoDto;
import com.bpass.backend.api.user.model.dto.UserInfoDto;
import com.bpass.backend.api.visit.model.Visits;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class VisitsDto {
  private long id;

  private UserInfoDto visitor;
  private StoreInfoDto store;
  private LocalDateTime entryTime;
  private LocalDateTime exitTime;

  public VisitsDto(Visits visits){
    this.visitor = new UserInfoDto(visits.getVisitor());
    this.store = new StoreInfoDto(visits.getStore());
    this.entryTime = visits.getEntryTime();
    this.exitTime = visits.getExitTime();
  }
}
