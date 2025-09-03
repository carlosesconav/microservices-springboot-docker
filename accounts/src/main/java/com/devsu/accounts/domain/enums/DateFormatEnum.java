package com.devsu.accounts.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum DateFormatEnum {

    MOVEMENT_FORMAT("dd/MM/yyyy");

    private final String value;

}
