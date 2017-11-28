package com.homework.profile.crm;

import com.homework.profile.domain.CustomerProfile;
import com.homework.profile.domain.payload.ResponsePayload;
import feign.Headers;
import feign.Param;
import feign.RequestLine;
import org.springframework.cloud.netflix.feign.FeignClient;

@FeignClient(name = "", url="/crm/api/profile")
public interface FeignCRMClient {

    @RequestLine("GET /{customerId}")
    ResponsePayload findById(@Param("customerId") String customerId);

    @RequestLine("POST")
    @Headers("Content-Type: application/json")
    ResponsePayload create(CustomerProfile customer);

    @RequestLine("DELETE /{customerId}")
    ResponsePayload delete(@Param("customerId") String customerId);

    @RequestLine("PUT")
    @Headers("Content-Type: application/json")
    ResponsePayload update(CustomerProfile customer);
}
