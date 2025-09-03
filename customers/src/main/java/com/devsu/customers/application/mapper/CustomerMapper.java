package com.devsu.customers.application.mapper;

import com.devsu.customers.application.dto.*;
import com.devsu.customers.domain.model.Customer;

import java.time.LocalDateTime;

public class CustomerMapper {


    public Customer toCustomerDomain(CustomerDTO customerDTO) {

        Customer customer = new Customer();
        customer.setName(customerDTO.getName());
        customer.setGender(customerDTO.getGender());
        customer.setAge(customerDTO.getAge());
        customer.setIdentification(customerDTO.getCustomerDoc().getIdentification());
        customer.setIdentificationType(customerDTO.getCustomerDoc().getIdentificationType());
        customer.setPassword(customerDTO.getCustomerInfo().getPassword());
        customer.setPhone(customerDTO.getCustomerInfo().getPhone());
        customer.setAddress(customerDTO.getCustomerInfo().getAddress());
        customer.setState(true);

        return customer;

    }

    public void toUpdateCustomer(Customer customer, CustomerDTO customerDTO) {

        customer.setName(customerDTO.getName());
        customer.setGender(customerDTO.getGender());
        customer.setAge(customerDTO.getAge());
        customer.setIdentification(customerDTO.getCustomerDoc().getIdentification());
        customer.setIdentificationType(customerDTO.getCustomerDoc().getIdentificationType());
        customer.setPassword(customerDTO.getCustomerInfo().getPassword());
        customer.setPhone(customerDTO.getCustomerInfo().getPhone());
        customer.setAddress(customerDTO.getCustomerInfo().getAddress());
        customer.setState(customerDTO.getState());

    }

    public CustomerDTO toCustomerDTO(Customer customer) {


        CustomerDocDTO customerDocDTO = new CustomerDocDTO();
        customerDocDTO.setIdentification(customer.getIdentification());
        customerDocDTO.setIdentificationType(customer.getIdentificationType());

        CustomerInfoDTO customerInfoDTO = new CustomerInfoDTO();
        customerInfoDTO.setAddress(customer.getAddress());
        customerInfoDTO.setPhone(customer.getPhone());

        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setId(customer.getId());
        customerDTO.setName(customer.getName());
        customerDTO.setGender(customer.getGender());
        customerDTO.setAge(customer.getAge());
        customerDTO.setCustomerDoc(customerDocDTO);
        customerDTO.setCustomerInfo(customerInfoDTO);
        customerDTO.setState(customer.getState());

        return customerDTO;

    }


    public CustomerResponseDTO responseDTO(String message, String status) {

        InfoResponseDTO infoResponseDTO = new InfoResponseDTO();
        infoResponseDTO.setMessage(message);
        infoResponseDTO.setStatus(status);

        CustomerResponseDTO customerResponseDTO = new CustomerResponseDTO();
        customerResponseDTO.setData(infoResponseDTO);
        customerResponseDTO.setOperationDate(LocalDateTime.now().toString());

        return customerResponseDTO;
    }

    public CustomerResponseDTO responseDTO(Object data) {

        CustomerResponseDTO customerResponseDTO = new CustomerResponseDTO();
        customerResponseDTO.setData(data);
        customerResponseDTO.setOperationDate(LocalDateTime.now().toString());

        return customerResponseDTO;
    }

}
