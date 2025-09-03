package com.devsu.accounts.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum QueryEnum {
    GET_CUSTOMER_NAME("SELECT name FROM customers WHERE id = :id"),
    GET_MOVEMENT_REPORT("SELECT * FROM movements m " +
            "WHERE m.account_id = :accountId " +
            "AND STR_TO_DATE(m.operation_date, '%d/%m/%Y') " +
            "BETWEEN STR_TO_DATE(:initDate, '%d/%m/%Y') " +
            "AND STR_TO_DATE(:endDate, '%d/%m/%Y')");

    private final String value;
}
