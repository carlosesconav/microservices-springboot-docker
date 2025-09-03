package com.devsu.customers.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ExceptionEnum {

    BAD_REQUEST("Bad Request", "400"),
    CONFLICT("The user already exists", "409"),
    NOT_FOUND("The user does not exist", "404"),
    NOT_FOUND_OP("An error occurred during the operation", "404"),
    INTERNAL_SERVER_ERROR("Internal Server Error", "500");

    private final String message;
    private final String httpCode;
}
