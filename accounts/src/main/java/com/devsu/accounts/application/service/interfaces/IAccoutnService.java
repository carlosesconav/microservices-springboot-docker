package com.devsu.accounts.application.service.interfaces;

import com.devsu.accounts.application.dto.AccountDTO;
import com.devsu.accounts.application.dto.ResponseDTO;
import com.devsu.accounts.domain.exception.ServiceException;

public interface IAccoutnService {

    ResponseDTO createAccount(AccountDTO accountDTO) throws ServiceException;

    ResponseDTO addBalance(AccountDTO accountDTO) throws ServiceException;

    ResponseDTO withdrawBalance(AccountDTO accountDTO) throws ServiceException;

    ResponseDTO getStateAccountReport(String accountNumber, String startDate, String endDate) throws ServiceException;

}
