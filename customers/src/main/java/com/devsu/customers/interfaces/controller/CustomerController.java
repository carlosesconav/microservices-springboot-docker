package com.devsu.customers.interfaces.controller;

import com.devsu.customers.application.dto.CustomerDTO;
import com.devsu.customers.application.dto.CustomerResponseDTO;
import com.devsu.customers.application.mapper.CustomerMapper;
import com.devsu.customers.application.service.interfaces.ICustomerService;
import com.devsu.customers.domain.enums.HttpCodeEnum;
import com.devsu.customers.domain.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping(path="/api/customers")
public class CustomerController {

    @Autowired
    private ICustomerService customerService;

    private final CustomerMapper customerMapper = new CustomerMapper();

    @PostMapping(path = "/add")
    public ResponseEntity<CustomerResponseDTO> addCustomer(@RequestBody CustomerDTO customerDTO) {
       CustomerResponseDTO responseDTO = null;
        try {
             responseDTO = customerService.createCustomer(customerDTO);
            return ResponseEntity.created(ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .build()
                    .toUri()).body(responseDTO);
        } catch (ServiceException se) {
            responseDTO = customerMapper.responseDTO(se.getMessage(), se.getCode());
            return ResponseEntity.status(Integer.parseInt(se.getCode())).body(responseDTO);

        } catch (Exception e) {
            responseDTO = customerMapper.responseDTO(e.getMessage(), HttpCodeEnum.INTERNAL_SERVER_ERROR.getValue());
            return ResponseEntity.internalServerError().body(responseDTO);
        }
    }

    @GetMapping(path = "/{identification}")
    public ResponseEntity<CustomerResponseDTO> getCustomerById(@PathVariable String identification) {
        CustomerResponseDTO responseDTO = null;
        try {
            responseDTO = customerService.getCustomerByIdentification(identification);
            return ResponseEntity.ok(responseDTO);
        } catch (ServiceException se) {
            responseDTO = customerMapper.responseDTO(se.getMessage(), se.getCode());
            return ResponseEntity.status(Integer.parseInt(se.getCode())).body(responseDTO);

        } catch (Exception e) {
            responseDTO = customerMapper.responseDTO(e.getMessage(), HttpCodeEnum.INTERNAL_SERVER_ERROR.getValue());
            return ResponseEntity.internalServerError().body(responseDTO);
        }
    }

    @GetMapping(path = "/list")
    public ResponseEntity<CustomerResponseDTO> listCustomers() {
        CustomerResponseDTO responseDTO = null;
        try {
            responseDTO = customerService.listCustomers();
            return ResponseEntity.ok(responseDTO);
        } catch (ServiceException se) {
            responseDTO = customerMapper.responseDTO(se.getMessage(), se.getCode());
            return ResponseEntity.status(Integer.parseInt(se.getCode())).body(responseDTO);

        } catch (Exception e) {
            responseDTO = customerMapper.responseDTO(e.getMessage(), HttpCodeEnum.INTERNAL_SERVER_ERROR.getValue());
            return ResponseEntity.internalServerError().body(responseDTO);
        }
    }

    @PutMapping(path = "/update/{identification}")
    public ResponseEntity<CustomerResponseDTO> updateCustomer(@PathVariable String identification, @RequestBody CustomerDTO customerDTO) {
        CustomerResponseDTO responseDTO = null;
        try {
            responseDTO = customerService.updateCustomer(identification, customerDTO);
            return ResponseEntity.ok(responseDTO);
        } catch (ServiceException se) {
            responseDTO = customerMapper.responseDTO(se.getMessage(), se.getCode());
            return ResponseEntity.status(Integer.parseInt(se.getCode())).body(responseDTO);

        } catch (Exception e) {
            responseDTO = customerMapper.responseDTO(e.getMessage(), HttpCodeEnum.INTERNAL_SERVER_ERROR.getValue());
            return ResponseEntity.internalServerError().body(responseDTO);
        }
    }

    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<CustomerResponseDTO> deleteCustomerById(@PathVariable String id) {
        CustomerResponseDTO responseDTO = null;
        try {
            responseDTO = customerService.deleteCustomerById(id);
            return ResponseEntity.ok(responseDTO);
        } catch (ServiceException se) {
            responseDTO = customerMapper.responseDTO(se.getMessage(), se.getCode());
            return ResponseEntity.status(Integer.parseInt(se.getCode())).body(responseDTO);

        } catch (Exception e) {
            responseDTO = customerMapper.responseDTO(e.getMessage(), HttpCodeEnum.INTERNAL_SERVER_ERROR.getValue());
            return ResponseEntity.internalServerError().body(responseDTO);
        }
    }

    @PatchMapping(path = "/update/state/{id}")
    public ResponseEntity<CustomerResponseDTO> updateStateById(@PathVariable String id , @RequestParam String state) {

        System.out.println("State param: " + state);
        CustomerResponseDTO responseDTO = null;
        try {
            responseDTO = customerService.updateCustomerState(id, Boolean.parseBoolean(state));
            return ResponseEntity.ok(responseDTO);
        } catch (ServiceException se) {
            responseDTO = customerMapper.responseDTO(se.getMessage(), se.getCode());
            return ResponseEntity.status(Integer.parseInt(se.getCode())).body(responseDTO);

        } catch (Exception e) {
            responseDTO = customerMapper.responseDTO(e.getMessage(), HttpCodeEnum.INTERNAL_SERVER_ERROR.getValue());
            return ResponseEntity.internalServerError().body(responseDTO);
        }
    }

}
