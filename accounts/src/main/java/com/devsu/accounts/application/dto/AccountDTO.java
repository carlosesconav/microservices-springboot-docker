package com.devsu.accounts.application.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AccountDTO {

    private Long accountId;

    private String accountType;

    private String accountNumber;

    private Double balance;

    private Long customerId;

    private Boolean state;

}
