package com.devsu.accounts.application.mapper;

import com.devsu.accounts.application.dto.MovementDTO;
import com.devsu.accounts.domain.enums.DateFormatEnum;
import com.devsu.accounts.domain.enums.OperationEnum;
import com.devsu.accounts.domain.model.Account;
import com.devsu.accounts.domain.model.Movements;
import com.devsu.accounts.domain.util.DateUtil;

import java.time.LocalDateTime;

public class MovementMapper {

    public MovementDTO toMovementDTO(
            double initBalance, double amonut,
            OperationEnum operationEnum, Account account
    ) {

        MovementDTO movementDTO = new MovementDTO();

        double newBalance = 0.0;

        if (operationEnum == OperationEnum.WITHDRAW) {

            newBalance = initBalance - amonut;

            movementDTO.setMovementType(OperationEnum.WITHDRAW.getValue() + "-" + amonut);
            movementDTO.setOperationDate(DateUtil.dateFormat(LocalDateTime.now(), DateFormatEnum.MOVEMENT_FORMAT.getValue()));
            movementDTO.setValue(String.valueOf(amonut));
            movementDTO.setBalance(String.valueOf(newBalance));
            movementDTO.setAccount(account);

            return movementDTO;
        }

        newBalance = initBalance + amonut;

        movementDTO.setMovementType(OperationEnum.DEPOSIT.getValue()+ "+" + amonut);
        movementDTO.setOperationDate(DateUtil.dateFormat(LocalDateTime.now(), DateFormatEnum.MOVEMENT_FORMAT.getValue()));
        movementDTO.setValue(String.valueOf(amonut));
        movementDTO.setBalance(String.valueOf(newBalance));
        movementDTO.setAccount(account);

        return movementDTO;
    }

    public Movements toMovementDomain(MovementDTO movementDTO) {

        Movements movements = new Movements();

        movements.setOperationDate(movementDTO.getOperationDate());
        movements.setValue(movementDTO.getValue());
        movements.setBalance(movementDTO.getBalance());
        movements.setMovementType(movementDTO.getMovementType());
        movements.setAccount(movementDTO.getAccount());

        return movements;
    }

    public MovementDTO toMovementDD(Movements movements) {

        MovementDTO movementDTO = new MovementDTO();

        movementDTO.setAccount(movementDTO.getAccount());
        movementDTO.setBalance(movements.getBalance());
        movementDTO.setMovementType(movements.getMovementType());
        movementDTO.setOperationDate(movements.getOperationDate());
        movementDTO.setValue(movements.getValue());

        return movementDTO;
    }

}
