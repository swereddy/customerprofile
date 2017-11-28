package com.homework.profile.service;

import com.homework.profile.crm.CRMClient;
import com.homework.profile.crm.FeignCRMClient;
import com.homework.profile.domain.CustomerProfile;
import com.homework.profile.domain.payload.ResponsePayload;
import org.springframework.context.annotation.Primary;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class CustomerProfileServiceImpl implements CustomerProfileService {

    @Inject
    private CRMClient crmClient;

    @Override
    public ResponsePayload update(CustomerProfile customerProfile) {
        return crmClient.update(customerProfile);
    }

    @Override
    public ResponsePayload delete(String customerId) {
        return crmClient.delete(customerId);
    }

    @Override
    public ResponsePayload create(CustomerProfile customerProfile) {
        return crmClient.create(customerProfile);
    }

    @Override
    public ResponsePayload get(String customerId) {
        return crmClient.findById(customerId);
    }


}
