package com.homework.profile.webchannel.controller;


import com.auth0.jwt.interfaces.DecodedJWT;
import com.homework.profile.util.JWTUtil;
import com.homework.profile.domain.CustomerProfile;
import com.homework.profile.domain.payload.ResponsePayload;
import com.homework.profile.exceptions.InvalidRequestException;
import com.homework.profile.service.CustomerProfileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/api/profile")
@Slf4j
public class CustomerProfileController {

    @Inject
    private CustomerProfileService customerProfileService;

    /**
     * @param customerId
     * @param jwt
     * @return
     */
    @GetMapping(value = "/{customerId}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Cacheable(value = "customerProfile", key = "#customerId")
    public ResponseEntity<ResponsePayload> getCustomer(final @PathVariable String customerId,
                                                       @NotNull final @RequestHeader(value = "x-cust-usertoken") String jwt) {

        validateJWT(jwt);

        ResponsePayload responsePayload = customerProfileService.get(customerId);
        return new ResponseEntity<ResponsePayload>(responsePayload, responsePayload.getApiResponseCode().getHttpStatus());
    }

    private void validateJWT(@NotNull @RequestHeader(value = "x-cust-usertoken") String jwt) {

        if (jwt == "") {
            throw new InvalidRequestException("missingJWT");
        }

        DecodedJWT decodedJWT = JWTUtil.getDecodedJwt(jwt);
        //TODO: check decoded JWT has customerId and matches to the customerId on which get/update/delete is performed
    }

    /**
     * @param customerProfile
     */
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE,
            headers = "Content-Type=application/json")
    public ResponseEntity<ResponsePayload> createCustomer(final @RequestBody @Valid CustomerProfile customerProfile) {

        if (customerProfile == null) {
            throw new InvalidRequestException("missingCustomerInfo");
        }

        ResponsePayload responsePayload = customerProfileService.create(customerProfile);
        return new ResponseEntity<ResponsePayload>(responsePayload, responsePayload.getApiResponseCode().getHttpStatus());
    }


    /**
     * @param customerId
     * @param customerProfile
     * @param jwt
     */
    @PutMapping(value = "/{customerId}", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE,
            headers = "Content-Type=application/json")
    @CachePut(value = "customerProfile", key = "#customerId")
    public ResponseEntity<ResponsePayload> updateCustomer(final @PathVariable String customerId,
                                                          final @RequestBody @Valid CustomerProfile customerProfile,
                                                          @NotNull final @RequestHeader(value = "x-cust-usertoken") String jwt) {

        validateJWT(jwt);
        customerProfile.setId(customerId);
        ResponsePayload responsePayload = customerProfileService.update(customerProfile);
        return new ResponseEntity<ResponsePayload>(responsePayload, responsePayload.getApiResponseCode().getHttpStatus());
    }

    /**
     * @param customerId
     * @param jwt
     */
    @DeleteMapping(value = "/{customerId}", headers = "Content-Type=application/json")
    @CacheEvict(value = "customerProfile", key = "#customerId")
    public ResponseEntity deleteCustomer(final @PathVariable String customerId,
                                         @NotNull final @RequestHeader(value = "x-cust-usertoken") String jwt) {

        validateJWT(jwt);
        ResponsePayload responsePayload = customerProfileService.delete(customerId);
        return new ResponseEntity(responsePayload.getApiResponseCode().getHttpStatus());
    }
}