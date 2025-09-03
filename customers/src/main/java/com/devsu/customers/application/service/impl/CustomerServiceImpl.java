package com.devsu.customers.application.service.impl;

import com.devsu.customers.application.dto.CustomerDTO;
import com.devsu.customers.application.dto.CustomerResponseDTO;
import com.devsu.customers.application.mapper.CustomerMapper;
import com.devsu.customers.application.service.interfaces.ICustomerService;
import com.devsu.customers.application.service.interfaces.IValidateService;
import com.devsu.customers.domain.enums.ExceptionEnum;
import com.devsu.customers.domain.enums.HttpCodeEnum;
import com.devsu.customers.domain.exception.ServiceException;
import com.devsu.customers.domain.model.Customer;
import com.devsu.customers.infrastructure.repository.ICustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements ICustomerService {

    @Autowired
    private ICustomerRepository customerRepository;

    private final IValidateService validateService = new ValidateServiceImpl();

    private final CustomerMapper customerMapper = new CustomerMapper();

    /**
     * @param customerDTO Data Transfer Object containing customer details.
     * @return CustomerResponseDTO containing the result of the operation.
     * @throws ServiceException Creates a new customer after validating the
     *                          input data and checking for duplicates.
     */
    @Override
    public CustomerResponseDTO createCustomer(CustomerDTO customerDTO) throws ServiceException {

        if (customerDTO == null || !validateService.isValidCustomer(customerDTO)) {
            throw new ServiceException(ExceptionEnum.BAD_REQUEST);
        }

        if (getByIdentification(customerDTO.getCustomerDoc().getIdentification()) != null) {
            throw new ServiceException(ExceptionEnum.CONFLICT);
        }

        customerRepository.save(customerMapper.toCustomerDomain(customerDTO));

        return customerMapper.responseDTO(
                "Customer created successfully",
                HttpCodeEnum.CREATED.getValue());

    }

    /**
     * @param identification identification of the customer to retrieve.
     * @return Retrieves a customer by their identification.
     * @throws ServiceException Throws NOT_FOUND exception if customer does not exist.
     * <p>
     * Gets a customer by their identification.
     * If the customer is found, it maps the customer
     */
    @Override
    public CustomerResponseDTO getCustomerByIdentification(String identification) throws ServiceException {

        Customer customer = getByIdentification(identification);

        if (customer == null) {
            throw new ServiceException(ExceptionEnum.NOT_FOUND);
        }

        return customerMapper.responseDTO(
                customerMapper.toCustomerDTO(customer)
        );

    }

    /**
     * @return Lists all customers in the system.
     * @throws ServiceException Throws NOT_FOUND exception if no customers exist.
     * <p>
     * Fetches all customers from the repository.
     * If no customers are found, throws a NOT_FOUND exception.
     */
    @Override
    public CustomerResponseDTO listCustomers() throws ServiceException {

        List<Customer> customers = customerRepository.findAll();

        if (customers.isEmpty()) {
            throw new ServiceException(ExceptionEnum.NOT_FOUND);
        }

        return customerMapper.responseDTO(
                customers.stream()
                        .map(customerMapper::toCustomerDTO)
                        .toList()
        );
    }

    /**
     *
     * @param identification Identification of the customer to update.
     * @param customerDTO Data Transfer Object containing updated customer details.
     * @return CustomerResponseDTO containing the result of the update operation.
     * @throws ServiceException Throws BAD_REQUEST if input is invalid,
     *                          NOT_FOUND if customer does not exist,
     *                          CONFLICT if updated identification conflicts with another customer.
     *
     * Updates an existing customer's details after validating input data
     */
    @Override
    @Transactional
    public CustomerResponseDTO updateCustomer(String identification, CustomerDTO customerDTO) throws ServiceException {

        if (customerDTO == null || !validateService.isValidCustomer(customerDTO)) {
            throw new ServiceException(ExceptionEnum.BAD_REQUEST);
        }

        Customer existingCustomer = getByIdentification(identification);
        if (existingCustomer == null) {
            throw new ServiceException(ExceptionEnum.NOT_FOUND);
        }

        Customer other = getByIdentification(customerDTO.getCustomerDoc().getIdentification());
        if (other != null && !other.getId().equals(existingCustomer.getId())) {
            throw new ServiceException(ExceptionEnum.CONFLICT);
        }

        customerMapper.toUpdateCustomer(existingCustomer, customerDTO);

        customerRepository.save(existingCustomer);

        return customerMapper.responseDTO(
                "Customer updated successfully",
                HttpCodeEnum.OK.getValue());
    }

    /**
     * @param id Customer ID to update state.
     * @param state New state to set for the customer.
     * @return Updates the state (active/inactive) of a customer by their ID.
     * @throws ServiceException Throws NOT_FOUND if customer does not exist or update fails.
     *
     * Updates a customer's state (active/inactive) based on the provided ID and state value.
     *
     */
    @Override
    @Transactional
    public CustomerResponseDTO updateCustomerState(String id, boolean state) throws ServiceException {
        Optional<Customer> customer = customerRepository.findById(Long.parseLong(id));

        if (customer.isEmpty()) {
            throw new ServiceException(ExceptionEnum.NOT_FOUND);
        }

        int result = customerRepository.updateCustomerState(Long.parseLong(id), state);

        if (result == 0) {
            throw new ServiceException(ExceptionEnum.NOT_FOUND_OP);
        }

        return customerMapper.responseDTO(
                "Customer state updated successfully",
                HttpCodeEnum.OK.getValue());
    }

    /**
     * @param id ID of the customer to delete.
     * @throws ServiceException Throws NOT_FOUND if customer does not exist.
     * @return Deletes a customer by their ID.
     *
     * Deletes a customer from the repository based on the provided ID.
     */
    @Override
    public CustomerResponseDTO deleteCustomerById(String id) throws ServiceException {
        Optional<Customer> customer = customerRepository.findById(Long.parseLong(id));

        if (customer.isEmpty()) {
            throw new ServiceException(ExceptionEnum.NOT_FOUND);
        }

        customerRepository.deleteById(Long.parseLong(id));
        return customerMapper.responseDTO(
                "The user has been successfully deleted",
                HttpCodeEnum.OK.getValue());
    }

    /**
     * @param identification Identification of the customer to retrieve.
     * @return Fetches a customer by their identification.
     * Returns null if not found.
     */
    private Customer getByIdentification(String identification) {
        return customerRepository.findByIdentification(identification).orElse(null);
    }

}
