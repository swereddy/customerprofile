package com.homework.profile.crm;


import com.homework.profile.domain.CustomerProfile;
import com.homework.profile.domain.payload.ResponsePayload;



public interface CRMClient {

    ResponsePayload update(CustomerProfile customerProfile);

    ResponsePayload delete(String customerId);

    ResponsePayload create(CustomerProfile customerProfile);

    ResponsePayload findById(String customerId);
}
