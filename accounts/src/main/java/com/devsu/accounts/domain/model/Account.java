package com.devsu.accounts.domain.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "accounts")
@Getter
@Setter
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id")
    private Long accountId;

    @Column(name = "account_type", length = 10, nullable = false)
    private String accountType;

    @Column(
            name = "account_number", length = 50,
            unique = true, nullable = false
    )
    private String accountNumber;

    @Column(name = "balance", nullable = false)
    private Double balance;

    @Column(name = "state", nullable = false)
    private Boolean state;

    @Column(name = "customer_id", nullable = false)
    private Long customerId;

}
