package com.bpass.backend.api.user.model.dto;

import com.bpass.backend.api.user.model.Store;
import lombok.Getter;

@Getter
public class StoreInfoDto {
    private long id;
    private String storeName;
    private String storePhoneNumber;
    private String address;

    public StoreInfoDto(Store store) {
        this.id = store.getId();
        this.storeName = store.getStoreName();
        this.storePhoneNumber = store.getStorePhoneNumber();
        this.address = store.getAddress();
    }
}
