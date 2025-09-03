package com.devsu.accounts.infrastructure.repository;

import com.devsu.accounts.domain.model.Movements;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IMovementRepository extends JpaRepository<Movements, Long> {

}
