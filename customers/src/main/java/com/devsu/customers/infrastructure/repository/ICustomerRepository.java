package com.devsu.customers.infrastructure.repository;

import com.devsu.customers.domain.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface ICustomerRepository extends JpaRepository<Customer, Long> {

    Optional<Customer> findByIdentification(String identification);

    @Modifying
    @Transactional
    @Query("UPDATE Customer c SET c.state = :state WHERE c.id = :id")
    int updateCustomerState(@Param("id") Long id, @Param("state") Boolean state);

}
