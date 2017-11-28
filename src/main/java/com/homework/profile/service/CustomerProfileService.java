package com.homework.profile.service;

import com.homework.profile.domain.CustomerProfile;
import com.homework.profile.domain.payload.ResponsePayload;


public interface CustomerProfileService {

    ResponsePayload update(CustomerProfile customerProfile);

    ResponsePayload delete(String customerId);

    ResponsePayload create(CustomerProfile customerProfile);

    ResponsePayload get(String customerId);

}
