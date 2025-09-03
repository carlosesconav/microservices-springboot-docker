package com.devsu.accounts.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ValidateEnum {

    VALIDATE_ACCOUNT_NUMBER("^\\d+$");

    private final String value;

}
