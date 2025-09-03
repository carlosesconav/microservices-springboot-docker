package com.devsu.customers.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum GenderTypeEnum {

    WOMAN("F"),
    MAN("M");

    private final String value;
}
