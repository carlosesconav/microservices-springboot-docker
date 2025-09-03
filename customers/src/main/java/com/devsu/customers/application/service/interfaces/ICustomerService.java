package com.devsu.customers.application.service.interfaces;

import com.devsu.customers.application.dto.CustomerDTO;
import com.devsu.customers.application.dto.CustomerResponseDTO;
import com.devsu.customers.domain.exception.ServiceException;

import java.util.UUID;

public interface ICustomerService {


    CustomerResponseDTO createCustomer(CustomerDTO customerto) throws ServiceException;

    CustomerResponseDTO getCustomerByIdentification(String identification) throws ServiceException;

    CustomerResponseDTO listCustomers() throws ServiceException;

    CustomerResponseDTO updateCustomer(String id, CustomerDTO customerDTO) throws ServiceException;

    CustomerResponseDTO updateCustomerState(String id, boolean state) throws ServiceException;

    CustomerResponseDTO deleteCustomerById(String id) throws ServiceException;

}
