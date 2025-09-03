package com.devsu.accounts.application.service.interfaces;

import com.devsu.accounts.application.dto.MovementDTO;

import java.util.List;

public interface IMovementService {

    void createMovement(MovementDTO movementDTO);

    List<MovementDTO> getMovementsByAccountAndDateRange(String accountNumber, String startDate, String endDate);


}
