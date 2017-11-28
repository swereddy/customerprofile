package com.homework.profile.domain.payload;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.homework.profile.domain.CustomerProfile;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponsePayload {

    private ApiResponseCode apiResponseCode;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private CustomerProfile customerProfile;

    public ResponsePayload(CustomerProfile customerProfile, ApiResponseCode apiResponseCode) {
        this.apiResponseCode = apiResponseCode;
        this.customerProfile = customerProfile;
    }

    public ResponsePayload(ApiResponseCode apiResponseCode) {
        this.apiResponseCode = apiResponseCode;
    }




}
