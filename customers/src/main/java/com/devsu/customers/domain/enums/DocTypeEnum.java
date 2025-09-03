package com.devsu.customers.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DocTypeEnum {

    CC("CC"),
    CE("CE"),
    NIT("NIT"),
    PASSPORT("PAS");

    private final String value;
}
