package com.devsu.accounts.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OperationEnum {


    DEPOSIT("DEPOSIT"),
    WITHDRAW("WITHDRAW");

    private final String value;

}
