package com.edu.Institiute.repo;

import com.edu.Institiute.entity.SalesOrder;
import com.edu.Institiute.entity.SalesOrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SalesOrderItemRepo extends JpaRepository<SalesOrderItem, Integer> {
    @Query(value = "SELECT * FROM salesOrderItem WHERE id=:salesOrderItemId", nativeQuery = true)
    SalesOrderItem getSalesOrderItemByProvideId(@Param("salesOrderItemId")int salesOrderItemId);

    @Query(value = "SELECT * FROM salesOrderItem WHERE id=:salesOrderItemId", nativeQuery = true)
    SalesOrderItem findBySalesOrderItemId(@Param("salesOrderItemId") int salesOrderItemId);

    @Query(value = "SELECT * FROM salesOrderItem WHERE id=:salesOrderItemId", nativeQuery = true)
    List<SalesOrderItem> getAllSalesOrderItem();

    @Query(value = "SELECT * FROM salesOrderItem WHERE id=:salesOrderItemId", nativeQuery = true)
    List<SalesOrderItem>getAllSalesOrderItemForProvidedId(@Param("salesOrderItemId") int  salesOrderItemId);

}
