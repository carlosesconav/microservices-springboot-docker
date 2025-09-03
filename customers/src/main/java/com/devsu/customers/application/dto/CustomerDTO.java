package com.devsu.customers.application.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomerDTO {

    private Long id;

    private String name;

    private int age;

    private String gender;

    private CustomerDocDTO customerDoc;

    private CustomerInfoDTO customerInfo;

    private Boolean state;
}
