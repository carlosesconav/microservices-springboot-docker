package com.devsu.accounts.application.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ReportDTO {

    private String customerName;

    private String accountNumber;

    private String accountType;

    private List<MovementDTO> movements;

}
