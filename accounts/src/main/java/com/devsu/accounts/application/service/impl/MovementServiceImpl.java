package com.devsu.accounts.application.service.impl;

import com.devsu.accounts.application.dto.MovementDTO;
import com.devsu.accounts.application.mapper.MovementMapper;
import com.devsu.accounts.application.service.interfaces.IMovementService;
import com.devsu.accounts.domain.model.Movements;
import com.devsu.accounts.infrastructure.repository.IMovementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovementServiceImpl implements IMovementService {

    @Autowired
    private IMovementRepository movementRepository;

    private final MovementMapper movementMapper = new MovementMapper();

    @Override
    public void createMovement(MovementDTO movementDTO) {
        Movements movements = movementMapper.toMovementDomain(movementDTO);
        movementRepository.save(movements);
    }

    @Override
    public List<MovementDTO> getMovementsByAccountAndDateRange(String accountNumber, String startDate, String endDate) {



        return List.of();
    }

}
