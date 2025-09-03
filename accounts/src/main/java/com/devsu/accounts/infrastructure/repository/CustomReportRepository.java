package com.devsu.accounts.infrastructure.repository;

import com.devsu.accounts.domain.enums.QueryEnum;
import com.devsu.accounts.domain.model.Movements;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CustomReportRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public String getCustomerName(Long accountId) {
        String query = QueryEnum.GET_CUSTOMER_NAME.getValue();
        return (String) entityManager.createNativeQuery(query)
                .setParameter("id", accountId)
                .getSingleResult();
    }

    public List<Movements> getMovementRange(Long accountId, String initDate, String endDate) {
        String query = QueryEnum.GET_MOVEMENT_REPORT.getValue();

        return entityManager.createNativeQuery(query, Movements.class)
                .setParameter("accountId", accountId)
                .setParameter("initDate", initDate) // "03/09/2025"
                .setParameter("endDate", endDate)   // "05/09/2025"
                .getResultList();
    }

}
