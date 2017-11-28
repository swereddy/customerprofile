package com.homework.profile.domain.payload;


import org.springframework.http.HttpStatus;

import java.util.Optional;
import java.util.stream.Stream;

/**
 * API return status codes.
 */
public enum ApiResponseCode {

    SUCCESS(HttpStatus.OK, "API-200", "Success"),
    UNAUTHORISED_REQUEST(HttpStatus.UNAUTHORIZED, "API-401", "Unauthorized request"),
    INVALID_REQUEST(HttpStatus.BAD_REQUEST, "API-420", "Invalid request"),
    PROVIDER_ERROR(HttpStatus.BAD_REQUEST, "API-430", "Provider error"),
    INVALID_DATA(HttpStatus.BAD_REQUEST, "API-454", "Invalid data"),
    SERVER_FAILURE(HttpStatus.INTERNAL_SERVER_ERROR, "API-520", "Server failure"),
    SERVICE_UNAVAILABLE(HttpStatus.SERVICE_UNAVAILABLE, "API-522", "Service Unavailable"),
    SERVER_TIMEOUT(HttpStatus.GATEWAY_TIMEOUT, "API-523", "Server Timeout");

    private HttpStatus httpStatus;
    private String code;
    private String message;


    ApiResponseCode(HttpStatus httpStatus, String code, String message) {
        this.httpStatus = httpStatus;
        this.code = code;
        this.message = message;
    }

    public static ApiResponseCode viaHttpCodeV1(final int httpCode) {
        ApiResponseCode result = null;
        for (ApiResponseCode c : ApiResponseCode.values()) {
            if (c.httpStatus.value() == httpCode) {
                result = c;
                break;
            }
        }
        return result;
    }

    public static ApiResponseCode viaHttpCode(HttpStatus httpStatus) {
        // todo review the performance, stream performance seems to suck; maybe just use an iterator
        Optional<ApiResponseCode> first = Stream.of(ApiResponseCode.values()).filter(f -> f.httpStatus == httpStatus).findFirst();
        //todo should this return a null if not found or a default
        return first.isPresent() ? first.get() : defaultApiFailureCode();
    }

    public static ApiResponseCode viaHttpCode(int httpCode) {
        // todo review the performance, stream performance seems to suck; maybe just use an iterator
        Optional<ApiResponseCode> first = Stream.of(ApiResponseCode.values()).filter(f -> f.httpStatus.value() == httpCode).findFirst();
        //todo should this return a null if not found or a default
        return first.isPresent() ? first.get() : defaultApiFailureCode();
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    private static ApiResponseCode defaultApiFailureCode() {
        return ApiResponseCode.SERVER_FAILURE;
    }

}
