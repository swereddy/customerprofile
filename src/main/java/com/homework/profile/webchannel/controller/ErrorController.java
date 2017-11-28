package com.homework.profile.webchannel.controller;

import com.homework.profile.domain.payload.ApiResponseCode;
import com.homework.profile.exceptions.InvalidRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice
public class ErrorController {

    @GetMapping("error")
    public ApiResponseCode index() {
        return ApiResponseCode.SERVER_FAILURE;
    }

    @ExceptionHandler(InvalidRequestException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiResponseCode handleInvalidRequestException(InvalidRequestException e) {
        if (e.getMessage().equals("missingJWT")) {
            return ApiResponseCode.UNAUTHORISED_REQUEST;
        }
        return ApiResponseCode.INVALID_REQUEST;
    }
}
