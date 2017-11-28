package com.homework.profile.crm;

import com.homework.profile.domain.payload.ApiResponseCode;
import com.homework.profile.domain.CustomerProfile;
import com.homework.profile.domain.payload.ResponsePayload;

import javax.inject.Named;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Named
public class MockCRMClientImpl implements CRMClient {

    private Map<String, CustomerProfile> customerProfiles = new HashMap<>();

    private static final AtomicInteger counter = new AtomicInteger();

    @Override
    public ResponsePayload update(CustomerProfile customerProfile) {
        if (customerProfiles.containsKey(customerProfile.getId())) {
            customerProfiles.put(customerProfile.getId(), customerProfile);
            ResponsePayload responsePayload = new ResponsePayload(customerProfile, ApiResponseCode.SUCCESS);
            return responsePayload;
        }
        return new ResponsePayload(ApiResponseCode.INVALID_REQUEST);
    }

    @Override
    public ResponsePayload delete(String customerId) {
        if (customerProfiles.containsKey(customerId)) {
            customerProfiles.remove(customerId);
            ResponsePayload responsePayload = new ResponsePayload(ApiResponseCode.SUCCESS);
            return responsePayload;
        }
        return new ResponsePayload(ApiResponseCode.INVALID_REQUEST);
    }

    @Override
    public ResponsePayload create(CustomerProfile customerProfile) {

        if (customerProfile == null) {
            return new ResponsePayload(ApiResponseCode.INVALID_REQUEST);
        }

        int customerId = counter.getAndIncrement();
        customerProfile.setId(String.valueOf(customerId));
        customerProfiles.put(String.valueOf(customerId), customerProfile);
        return new ResponsePayload(customerProfile, ApiResponseCode.SUCCESS);
    }

    @Override
    public ResponsePayload findById(String customerId) {
        if (customerProfiles.containsKey(customerId)) {
            ResponsePayload responsePayload = new ResponsePayload(customerProfiles.get(customerId), ApiResponseCode.SUCCESS);
            return responsePayload;
        }
        return new ResponsePayload(ApiResponseCode.INVALID_REQUEST);
    }
}
