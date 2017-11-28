package com.homework.profile.domain;


public final class CustomerProfileMother {

    private CustomerProfileMother() {
    }

    public static CustomerProfile getCustomerProfile() {
        CustomerProfile customer = new CustomerProfile();
        customer.setDob("10/10/1990");
        customer.setFirstName("Customer");
        customer.setLastName("Profile");
        customer.getAddresses().add(getAddress());
        return customer;
    }

    private static Address getAddress() {
        Address address = new Address();
        address.setAddressType(AddressType.HOME_ADDRESS);
        address.setAddressLine1("100 George St");
        address.setSuburb("Sydney");
        address.setState("NSW");
        address.setCountry("AUSTRALIA");
        address.setPostCode("2000");
        return address;
    }

}
