package com.homework.profile.service;


import com.homework.profile.crm.MockCRMClientImpl;
import com.homework.profile.domain.CustomerProfile;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class CustomerProfileServiceTest {

    @InjectMocks
    private CustomerProfileServiceImpl customerProfileService;

    @Mock
    private MockCRMClientImpl crmClient;

    @Test
    public void verifyCreate() {
        customerProfileService.create(any(CustomerProfile.class));
        verify(crmClient).create(any(CustomerProfile.class));
    }

    @Test
    public void verifyGet() {
        customerProfileService.get(any());
        verify(crmClient).findById(any());
    }

    @Test
    public void verifyUpdate() {
        customerProfileService.update(any(CustomerProfile.class));
        verify(crmClient).update(any(CustomerProfile.class));
    }

    @Test
    public void verifyDelete() {
        customerProfileService.delete(any());
        verify(crmClient).delete(any());
    }


}
