package com.devsu.accounts.application.service.impl;

import com.devsu.accounts.application.dto.AccountDTO;
import com.devsu.accounts.application.service.interfaces.IValidateService;
import com.devsu.accounts.domain.enums.AccountTypeEnum;
import com.devsu.accounts.domain.enums.ValidateEnum;

import java.util.Arrays;

public class ValidateServiceImpl implements IValidateService {

    /**
     * @param accountDTO accountDTO to be validated
     * @return boolean
     *
     * Validations to be implemented
     */
    @Override
    public boolean isValidAccount(AccountDTO accountDTO) {
        return isValidAccountType(accountDTO.getAccountType())
                && isValidAccountNumber(accountDTO.getAccountNumber());
    }

    private boolean isValidString(String value) {
        return value != null && !value.trim().isEmpty();
    }

    private boolean isValidAccountType(String accountType) {
        return Arrays.stream(AccountTypeEnum.values())
                .anyMatch(e -> e.getValue().equalsIgnoreCase(accountType));
    }

    private boolean isValidAccountNumber(String accountNumber) {
        return isValidString(accountNumber)
                && accountNumber.matches(
                ValidateEnum.
                        VALIDATE_ACCOUNT_NUMBER.getValue()
        );
    }
}
