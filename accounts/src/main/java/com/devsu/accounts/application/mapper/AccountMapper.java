package com.devsu.accounts.application.mapper;

import com.devsu.accounts.application.dto.AccountDTO;
import com.devsu.accounts.domain.model.Account;

public class AccountMapper {


    public Account toAccountDomain(AccountDTO accountDTO) {

        Account account = new Account();
        account.setAccountNumber(accountDTO.getAccountNumber());
        account.setAccountType(accountDTO.getAccountType());
        account.setBalance(0.0);
        account.setState(accountDTO.getState());
        account.setCustomerId(accountDTO.getCustomerId());
        return account;

    }

    public Account toUpdateBalance(Account account, double newBalance) {
        account.setBalance(newBalance);
        return account;
    }

}
