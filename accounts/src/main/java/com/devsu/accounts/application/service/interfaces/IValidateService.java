package com.devsu.accounts.application.service.interfaces;

import com.devsu.accounts.application.dto.AccountDTO;

public interface IValidateService {

    boolean isValidAccount(AccountDTO accountDTO);

}
