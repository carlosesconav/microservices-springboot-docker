package com.devsu.customers.domain.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "customers")
@Getter
@Setter
public class Customer extends Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "password", length = 200, nullable = false)
    private String password;

    @Column(name = "state", nullable = false)
    private Boolean state;
}
