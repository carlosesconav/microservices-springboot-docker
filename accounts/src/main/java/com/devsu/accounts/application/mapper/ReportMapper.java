package com.devsu.accounts.application.mapper;

import com.devsu.accounts.application.dto.MovementDTO;
import com.devsu.accounts.application.dto.ReportDTO;

import java.util.List;

public class ReportMapper {

    public ReportDTO toReportDTO(String customerName, String accountNumber, String accountType, List<MovementDTO> movements) {

        ReportDTO reportDTO = new ReportDTO();
        reportDTO.setCustomerName(customerName);
        reportDTO.setAccountNumber(accountNumber);
        reportDTO.setAccountType(accountType);

        reportDTO.setMovements(movements);

        return reportDTO;

    }

}
