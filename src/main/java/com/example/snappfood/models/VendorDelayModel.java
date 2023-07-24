package com.example.snappfood.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class VendorDelayModel {
    private String vendorUid;
    private String vendorName;
    private int delay;
}
