package com.devsu.accounts.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum AccountTypeEnum {

    CAHO("AHORRO"),
    CCTE("CORRIENTE"),
    DBMO("BAJO MONTO");


    private final String value;

}
