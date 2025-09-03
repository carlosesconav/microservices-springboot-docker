package com.devsu.customers.application.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomerInfoDTO {

    private String phone;

    private String address;

    private String password;

}
