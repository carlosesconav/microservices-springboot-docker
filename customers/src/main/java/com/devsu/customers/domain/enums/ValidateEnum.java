package com.devsu.customers.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
public enum ValidateEnum {

    VALIDATE_PHONE("^\\d+$");

    private final String value;

}
