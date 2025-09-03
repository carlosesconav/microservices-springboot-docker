package com.devsu.customers;

import com.devsu.customers.application.dto.CustomerDTO;
import com.devsu.customers.application.dto.CustomerDocDTO;
import com.devsu.customers.application.dto.CustomerInfoDTO;
import com.devsu.customers.application.service.impl.ValidateServiceImpl;
import com.devsu.customers.application.service.interfaces.IValidateService;
import com.devsu.customers.domain.enums.DocTypeEnum;
import com.devsu.customers.domain.enums.ValidateEnum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

@SpringBootTest
class CustomersApplicationTests {

    private IValidateService validateService = new ValidateServiceImpl();

	@Test
	void contextLoads() {

        CustomerDTO customerDTO = new CustomerDTO();

        CustomerDocDTO customerDocDTO = new CustomerDocDTO();
        customerDocDTO.setIdentification("1234567890");
        customerDocDTO.setIdentificationType("CC");

        CustomerInfoDTO customerInfoDTO = new CustomerInfoDTO();
        customerInfoDTO.setPhone("3002134432");
        customerInfoDTO.setAddress("Villa melisa");
        customerInfoDTO.setPassword("123");


        customerDTO.setName("Carlos Escobar");
        customerDTO.setAge(24);
        customerDTO.setGender("M");
        customerDTO.setCustomerDoc(customerDocDTO);
        customerDTO.setCustomerInfo(customerInfoDTO);

        Assertions.assertTrue(validateService.isValidCustomer(customerDTO));
	}

}
