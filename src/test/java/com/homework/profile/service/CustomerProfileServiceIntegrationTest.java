package com.homework.profile.service;

import com.homework.profile.domain.CustomerProfile;
import com.homework.profile.domain.payload.ApiResponseCode;
import com.homework.profile.domain.payload.ResponsePayload;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.inject.Inject;

import static com.homework.profile.domain.CustomerProfileMother.getCustomerProfile;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerProfileServiceIntegrationTest {

    @Inject
    private CustomerProfileService customerProfileService;

    @Test
    public void createCustomerProfile_ReturnSuccess() {
      ResponsePayload resposePayload =  customerProfileService.create(getCustomerProfile());
      assertEquals(resposePayload.getApiResponseCode(), ApiResponseCode.SUCCESS);
      assertNotNull(resposePayload.getCustomerProfile().getId());
    }

    @Test
    public void createCustomerProfile_ReturnInvalidRequest() {
        ResponsePayload resposePayload =  customerProfileService.create(null);
        assertEquals(resposePayload.getApiResponseCode(), ApiResponseCode.INVALID_REQUEST);
    }

    @Test
    public void getCustomerProfile_ReturnSuccess() {
        ResponsePayload resposePayload =  customerProfileService.create(getCustomerProfile());
        ResponsePayload payload = customerProfileService.get(resposePayload.getCustomerProfile().getId());
        assertEquals(payload.getApiResponseCode(), ApiResponseCode.SUCCESS);
        assertNotNull(payload.getCustomerProfile().getId());
    }

    @Test
    public void getCustomerProfile_ReturnInvalidRequest() {
        ResponsePayload resposePayload =  customerProfileService.get("9999");
        assertEquals(resposePayload.getApiResponseCode(), ApiResponseCode.INVALID_REQUEST);
    }

    @Test
    public void updateCustomerProfile_ReturnSuccess() {
        ResponsePayload resposePayload =  customerProfileService.create(getCustomerProfile());
        CustomerProfile customerProfile = resposePayload.getCustomerProfile();
        customerProfile.setDob("11/10/1998");
        ResponsePayload payload = customerProfileService.update(customerProfile);
        assertEquals(payload.getApiResponseCode(), ApiResponseCode.SUCCESS);
        assertNotNull(payload.getCustomerProfile().getId());
    }

    @Test
    public void updateCustomerProfile_ReturnInvalidRequest() {
        CustomerProfile customerProfile = getCustomerProfile();
        customerProfile.setId("2211");
        ResponsePayload resposePayload =  customerProfileService.update(customerProfile);
        assertEquals(resposePayload.getApiResponseCode(), ApiResponseCode.INVALID_REQUEST);
    }


    @Test
    public void deleteCustomerProfile_ReturnSuccess() {
        ResponsePayload resposePayload =  customerProfileService.create(getCustomerProfile());
        ResponsePayload payload = customerProfileService.delete(resposePayload.getCustomerProfile().getId());
        assertEquals(payload.getApiResponseCode(), ApiResponseCode.SUCCESS);
    }

    @Test
    public void deleteCustomerProfile_ReturnInvalidRequest() {
        ResponsePayload resposePayload =  customerProfileService.delete("9999");
        assertEquals(resposePayload.getApiResponseCode(), ApiResponseCode.INVALID_REQUEST);
    }


}
