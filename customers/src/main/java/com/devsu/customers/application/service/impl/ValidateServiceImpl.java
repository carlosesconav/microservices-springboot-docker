package com.devsu.customers.application.service.impl;

import com.devsu.customers.application.dto.CustomerDTO;
import com.devsu.customers.application.service.interfaces.IValidateService;
import com.devsu.customers.domain.enums.DocTypeEnum;
import com.devsu.customers.domain.enums.GenderTypeEnum;
import com.devsu.customers.domain.enums.ValidateEnum;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class ValidateServiceImpl implements IValidateService {

    /**
     * @param customerDTO
     * @return
     */
    @Override
    public boolean isValidCustomer(CustomerDTO customerDTO) {
        return isValidString(customerDTO.getName())
                && isValidAge(customerDTO.getAge())
                && isValidGender(customerDTO.getGender())
                && isValidDocumentNumber(
                customerDTO.getCustomerDoc()
                        .getIdentification())
                && isValidDocumentType(
                customerDTO.getCustomerDoc()
                        .getIdentificationType())
                && isValidPhoneNumber(
                customerDTO.getCustomerInfo()
                        .getPhone())
                && isValidString(
                customerDTO.getCustomerInfo()
                        .getAddress())
                && isValidString(customerDTO.getCustomerInfo()
                .getPassword());
    }

    private boolean isValidDocumentType(String docType) {
        return Arrays.stream(DocTypeEnum.values())
                .anyMatch(e -> e.getValue().equalsIgnoreCase(docType));
    }

    private boolean isValidString(String value) {
        return value != null && !value.trim().isEmpty();
    }

    private boolean isValidGender(String gender) {
        return Arrays.stream(GenderTypeEnum.values())
                .anyMatch(e -> e.getValue().equalsIgnoreCase(gender));
    }

    private boolean isValidAge(int age) {
        return age > 0;
    }

    private boolean isValidDocumentNumber(String docNumber) {
        return isValidString(docNumber)
                && docNumber.matches(
                ValidateEnum.
                        VALIDATE_PHONE.getValue()
        );
    }

    private boolean isValidPhoneNumber(String phoneNumber) {
        return isValidString(phoneNumber)
                && phoneNumber.matches(
                ValidateEnum.
                        VALIDATE_PHONE.getValue()
        );
    }

}
