package com.homework.profile.domain;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Data
public class CustomerProfile {

    private String id;
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    @NotNull
    private String dob;
    private List<Address> addresses = new ArrayList<>();
    private String emailAddress;
}
