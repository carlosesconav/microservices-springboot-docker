package com.devsu.accounts.application.service.impl;

import com.devsu.accounts.application.dto.AccountDTO;
import com.devsu.accounts.application.dto.MovementDTO;
import com.devsu.accounts.application.dto.ReportDTO;
import com.devsu.accounts.application.dto.ResponseDTO;
import com.devsu.accounts.application.mapper.AccountMapper;
import com.devsu.accounts.application.mapper.MovementMapper;
import com.devsu.accounts.application.mapper.ReportMapper;
import com.devsu.accounts.application.mapper.ResponseMapper;
import com.devsu.accounts.application.service.interfaces.IAccoutnService;
import com.devsu.accounts.application.service.interfaces.IMovementService;
import com.devsu.accounts.application.service.interfaces.IValidateService;
import com.devsu.accounts.domain.enums.ExceptionEnum;
import com.devsu.accounts.domain.enums.HttpCodeEnum;
import com.devsu.accounts.domain.enums.OperationEnum;
import com.devsu.accounts.domain.exception.ServiceException;
import com.devsu.accounts.domain.model.Account;
import com.devsu.accounts.domain.model.Movements;
import com.devsu.accounts.infrastructure.repository.CustomReportRepository;
import com.devsu.accounts.infrastructure.repository.IAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@Service
public class AccoutnServiceImpl implements IAccoutnService {

    @Autowired
    private IAccountRepository accountRepository;

    @Autowired
    private IMovementService movementService;

    @Autowired
    private CustomReportRepository customRepository;

    private final AccountMapper accountMapper = new AccountMapper();

    private final ReportMapper reportMapper = new ReportMapper();

    private final ResponseMapper responseMapper = new ResponseMapper();

    private final MovementMapper movementMapper = new MovementMapper();

    private final IValidateService validateService = new ValidateServiceImpl();

    /**
     * @param accountDTO Data Transfer Object containing account details.
     * @return ResponseDTO containing the result of the operation.
     * @throws ServiceException Creates a new account after validating the
     *                          input data.
     *
     */
    @Override
    public ResponseDTO createAccount(AccountDTO accountDTO) throws ServiceException {

        if (accountDTO == null || !validateService.isValidAccount(accountDTO)) {
            throw new ServiceException(ExceptionEnum.BAD_REQUEST);
        }

        Account account = accountMapper.toAccountDomain(accountDTO);

        accountRepository.save(account);

        return responseMapper.responseDTO(
                "Account created successfully",
                HttpCodeEnum.CREATED.getValue());
    }

    @Override
    @Transactional
    public ResponseDTO addBalance(AccountDTO accountDTO) throws ServiceException {

        Account account = findByAccountNumber(accountDTO.getAccountNumber());

        if (account == null) {
            throw new ServiceException(ExceptionEnum.NOT_FOUND);
        }
        if (accountDTO.getBalance() <= 0) {
            throw new ServiceException(ExceptionEnum.BAD_REQUEST);
        }

        double newBalance = account.getBalance() + accountDTO.getBalance();

        movementService.createMovement(movementMapper.toMovementDTO(
                account.getBalance(), accountDTO.getBalance(),
                OperationEnum.DEPOSIT, account
        ));

        accountMapper.toUpdateBalance(account, newBalance);

        accountRepository.save(account);

        return responseMapper.responseDTO(
                "Balance updated successfully",
                HttpCodeEnum.OK.getValue());
    }

    @Override
    @Transactional
    public ResponseDTO withdrawBalance(AccountDTO accountDTO) throws ServiceException {

        Account account = findByAccountNumber(accountDTO.getAccountNumber());

        if (account == null) {
            throw new ServiceException(ExceptionEnum.NOT_FOUND);
        }

        if (accountDTO.getBalance() <= 0 || account.getBalance() < accountDTO.getBalance()) {
            throw new ServiceException(ExceptionEnum.BAD_REQUEST);
        }

        double newBalance = account.getBalance() - accountDTO.getBalance();

        movementService.createMovement(movementMapper.toMovementDTO(
                account.getBalance(), accountDTO.getBalance(),
                OperationEnum.WITHDRAW, account
        ));

        accountMapper.toUpdateBalance(account, newBalance);

        accountRepository.save(account);

        return responseMapper.responseDTO(
                "Balance updated successfully",
                HttpCodeEnum.OK.getValue());
    }

    @Override
    public ResponseDTO getStateAccountReport(String accountNumber, String startDate, String endDate) throws ServiceException {

        Account account = findByAccountNumber(accountNumber);

        if (account == null) {
            throw new ServiceException(ExceptionEnum.NOT_FOUND);
        }

        String customerName = customRepository.getCustomerName(account.getCustomerId());

        List<Movements> movements = customRepository.getMovementRange(
                account.getAccountId(),
                startDate,
                endDate
        );

        if (movements == null || movements.isEmpty()) {
            throw new ServiceException(ExceptionEnum.NOT_FOUND);
        }

        List<MovementDTO> movementsList = movements.stream()
                .map(movementMapper::toMovementDD)
                .toList();

        ReportDTO reportDTO = reportMapper.toReportDTO(
                customerName,
                account.getAccountNumber(),
                account.getAccountType(),
                movementsList
        );

        return responseMapper.responseDTO(reportDTO);
    }


    private Account findByAccountNumber(String accountNumber) {
        return accountRepository.findByAccountNumber(accountNumber).orElse(null);
    }

}
