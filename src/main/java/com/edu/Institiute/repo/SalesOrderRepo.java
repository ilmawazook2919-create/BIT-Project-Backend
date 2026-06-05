package com.edu.Institiute.repo;

import com.edu.Institiute.entity.SalesOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SalesOrderRepo extends JpaRepository<SalesOrder, Integer> {
    @Query(value = "SELECT * FROM salesOrder WHERE id=:salesOrderId", nativeQuery = true)
    SalesOrder getSalesOrderByProvideId(@Param("salesOrderId")int salesOrderId);

    @Query(value = "SELECT * FROM salesOrder WHERE id=:salesOrderId", nativeQuery = true)
    SalesOrder findBySalesOrderId(@Param("salesOrderId") int salesOrderId);

    @Query(value = "SELECT * FROM salesOrder WHERE id=:salesOrderId", nativeQuery = true)
    List<SalesOrder> getAllSalesOrder();

    @Query(value = "SELECT * FROM salesOrder WHERE id=:salesOrderId", nativeQuery = true)
    List<SalesOrder>getAllSalesOrderForProvidedId(@Param("salesOrderId") int  salesOrderId);
}

