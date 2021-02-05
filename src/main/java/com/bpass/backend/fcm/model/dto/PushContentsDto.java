package com.bpass.backend.fcm.model.dto;

import com.bpass.backend.api.visit.model.Visits;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PushContentsDto {
  String title;
  String body;
  String storeName;
  String storePhoneNumber;
  String address;
  String latitude;
  String longitude;
  public PushContentsDto(Visits visits){
    this.title = "B pass";
    this.body = "확진자와 동선이 겹쳤어요";
    this.storeName = visits.getStore().getStoreName();
    this.storePhoneNumber = visits.getStore().getStorePhoneNumber();
    this.address = visits.getStore().getAddress();
    this.latitude = visits.getStore().getLatitude();
    this.longitude = visits.getStore().getLongitude();
  }
}
