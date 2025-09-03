package com.devsu.accounts.domain.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "movements")
@Getter
@Setter
public class Movements {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "movement_id")
    private Long movementId;

    @Column(name = "operation_date", length = 50, nullable = false)
    private String operationDate;


    @Column(name = "value", length = 50, nullable = false)
    private String value;


    @Column(name = "balance", length = 50, nullable = false)
    private String balance;


    @Column(name = "movement_type", nullable = false)
    private String movementType;

    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;

}
