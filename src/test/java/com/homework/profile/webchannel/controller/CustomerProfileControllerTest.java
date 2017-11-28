package com.homework.profile.webchannel.controller;

import com.google.gson.Gson;
import com.homework.profile.domain.CustomerProfile;
import com.homework.profile.domain.payload.ApiResponseCode;

import com.homework.profile.domain.payload.ResponsePayload;

import com.homework.profile.service.CustomerProfileServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static com.homework.profile.domain.CustomerProfileMother.getCustomerProfile;
import static com.homework.profile.domain.payload.ApiResponseCode.UNAUTHORISED_REQUEST;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(CustomerProfileController.class)
public class CustomerProfileControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerProfileServiceImpl customerProfileService;

    @Test
    public void getCustomerProfile_ShouldReturnValidProfile() throws Exception {

        CustomerProfile customerProfile = getCustomerProfile();
        customerProfile.setId("1111");
        ResponsePayload responsePayload = new ResponsePayload(customerProfile, ApiResponseCode.SUCCESS);
        when(customerProfileService.get("1111")).thenReturn(responsePayload);

        mockMvc.perform(get("/api/profile/1111")
                .header("x-cust-usertoken", "jwt"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$..customerProfile").exists())
                .andExpect(jsonPath("$..addresses").exists())
                .andExpect(jsonPath("$..apiResponseCode").value("SUCCESS"))
                .andExpect(jsonPath("$..customerProfile.id").value("1111"))
                .andExpect(jsonPath("$..customerProfile.dob").value("10/10/1990"))
                .andExpect(jsonPath("$..customerProfile.firstName").value("Customer"))
                .andExpect(jsonPath("$..customerProfile.lastName").value("Profile"));
    }

    @Test
    public void getCustomerProfile_ReturnInvalidRequest() throws Exception {
        CustomerProfile customerProfile = getCustomerProfile();
        customerProfile.setId("111");
        ResponsePayload responsePayload = new ResponsePayload(ApiResponseCode.INVALID_REQUEST);
        when(customerProfileService.get("111")).thenReturn(responsePayload);

        mockMvc.perform(get("/api/profile/111")
                .header("x-cust-usertoken", "jwt"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$..customerProfile").doesNotExist())
                .andExpect(jsonPath("$..addresses").doesNotExist())
                .andExpect(jsonPath("$..apiResponseCode").value("INVALID_REQUEST"));
    }

    @Test
    public void getCustomerProfile_WithMissingJWT() throws Exception {
        mockMvc.perform(get("/api/profile/1111")
                .header("x-cust-usertoken", ""))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$..customerProfile").doesNotExist())
                .andExpect(jsonPath("$..addresses").doesNotExist())
                .andExpect(content().string("\""+UNAUTHORISED_REQUEST+"\""));
    }

    @Test
    public void deleteCustomerProfile_ShouldReturnSuccess() throws Exception {
        ResponsePayload responsePayload = new ResponsePayload(ApiResponseCode.SUCCESS);
        when(customerProfileService.delete("1111")).thenReturn(responsePayload);

        mockMvc.perform(delete("/api/profile/1111")
                .contentType(MediaType.APPLICATION_JSON)
                .header("x-cust-usertoken", "jwt"))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteCustomerProfile_ReturnInvalidRequest() throws Exception {
        ResponsePayload responsePayload = new ResponsePayload(ApiResponseCode.INVALID_REQUEST);
        when(customerProfileService.delete("111")).thenReturn(responsePayload);

        mockMvc.perform(delete("/api/profile/111")
                .contentType(MediaType.APPLICATION_JSON)
                .header("x-cust-usertoken", "jwt"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void deleteCustomerProfile_WithMissingJWT() throws Exception {
        mockMvc.perform(delete("/api/profile/1111")
                .contentType(MediaType.APPLICATION_JSON)
                .header("x-cust-usertoken", ""))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("\""+UNAUTHORISED_REQUEST+"\""));
    }

    @Test
    public void updateCustomerProfile_WithMissingJWT() throws Exception {

        mockMvc.perform(put("/api/profile/1111")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new Gson().toJson(getCustomerProfile()))
                .header("x-cust-usertoken", ""))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$..customerProfile").doesNotExist())
                .andExpect(jsonPath("$..addresses").doesNotExist())
                .andExpect(content().string("\""+UNAUTHORISED_REQUEST+"\""));
    }

    @Test
    public void updateCustomerProfile_ShouldReturnSuccess() throws Exception {
        CustomerProfile customerProfile = getCustomerProfile();
        customerProfile.setId("1111");
        customerProfile.setLastName("Profiles");
        ResponsePayload responsePayload = new ResponsePayload(customerProfile, ApiResponseCode.SUCCESS);
        when(customerProfileService.update(customerProfile)).thenReturn(responsePayload);

        mockMvc.perform(put("/api/profile/1111")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new Gson().toJson(customerProfile))
                .header("x-cust-usertoken", "jwt"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$..customerProfile").exists())
                .andExpect(jsonPath("$..addresses").exists())
                .andExpect(jsonPath("$..apiResponseCode").value("SUCCESS"))
                .andExpect(jsonPath("$..customerProfile.id").value("1111"))
                .andExpect(jsonPath("$..customerProfile.dob").value("10/10/1990"))
                .andExpect(jsonPath("$..customerProfile.firstName").value("Customer"))
                .andExpect(jsonPath("$..customerProfile.lastName").value("Profiles"));
    }

    @Test
    public void updateCustomerProfile_ReturnInvalidRequest() throws Exception {
        CustomerProfile customerProfile = getCustomerProfile();
        customerProfile.setId("111");
        ResponsePayload responsePayload = new ResponsePayload(ApiResponseCode.INVALID_REQUEST);
        when(customerProfileService.update(customerProfile)).thenReturn(responsePayload);

        mockMvc.perform(put("/api/profile/111")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new Gson().toJson(customerProfile))
                .header("x-cust-usertoken", "jwt"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$..customerProfile").doesNotExist())
                .andExpect(jsonPath("$..addresses").doesNotExist())
                .andExpect(jsonPath("$..apiResponseCode").value("INVALID_REQUEST"));
    }

    @Test
    public void createCustomerProfile_ShouldReturnSuccess() throws Exception {
        CustomerProfile customerProfile = getCustomerProfile();
        customerProfile.setId("1111");
        ResponsePayload responsePayload = new ResponsePayload(customerProfile, ApiResponseCode.SUCCESS);
        when(customerProfileService.create(getCustomerProfile())).thenReturn(responsePayload);

        mockMvc.perform(post("/api/profile")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new Gson().toJson(getCustomerProfile()))
                .header("x-cust-usertoken", "jwt"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$..customerProfile").exists())
                .andExpect(jsonPath("$..addresses").exists())
                .andExpect(jsonPath("$..apiResponseCode").value("SUCCESS"))
                .andExpect(jsonPath("$..customerProfile.id").value("1111"))
                .andExpect(jsonPath("$..customerProfile.dob").value("10/10/1990"))
                .andExpect(jsonPath("$..customerProfile.firstName").value("Customer"))
                .andExpect(jsonPath("$..customerProfile.lastName").value("Profile"));
    }

}

