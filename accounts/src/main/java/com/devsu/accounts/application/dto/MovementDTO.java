package com.devsu.accounts.application.dto;


import com.devsu.accounts.domain.model.Account;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MovementDTO {

    private String operationDate;

    private String value;

    private String balance;

    private String movementType;

    private Account account;


}
