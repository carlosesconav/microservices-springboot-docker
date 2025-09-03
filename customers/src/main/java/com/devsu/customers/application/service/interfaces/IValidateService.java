package com.devsu.customers.application.service.interfaces;

import com.devsu.customers.application.dto.CustomerDTO;

public interface IValidateService {


    boolean isValidCustomer(CustomerDTO customerDTO);

}
