package com.homework.profile.domain;

import lombok.Data;

@Data
public class Address {
    private String addressLine1;
    private String addressLine2;
    private String suburb;
    private String state;
    private String postCode;
    private String country;
    private AddressType addressType;
}
