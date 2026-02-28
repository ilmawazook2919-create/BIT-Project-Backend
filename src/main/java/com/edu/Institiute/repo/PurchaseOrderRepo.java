package com.edu.Institiute.repo;

import com.edu.Institiute.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PurchaseOrderRepo extends JpaRepository<PurchaseOrder, Integer> {
    @Query(value = "SELECT * FROM PurchaseOrder WHERE id=:purchaseOrderId", nativeQuery = true)
    PurchaseOrder getPurchaseOrderByProvideID(@Param("purchaseOrderId") int purchaseOrderId);
    @Query(value = "SELECT * FROM PurchaseOrder WHERE id=:PurchaseOrderId", nativeQuery = true)
    PurchaseOrder findByPurchaseOrderId(@Param("PurchaseOrderId") int PurchaseOrderId);
    @Query(value = "SELECT * FROM PurchaseOrder WHERE id=:PurchaseOrderId", nativeQuery = true)
    List<PurchaseOrder> getAllPurchaseOrder();
    @Query(value = "SELECT * FROM PurchaseOrderId WHERE id=:PurchaseOrderId", nativeQuery = true)
    List<PurchaseOrder>getAllPurchaseOrderForProvidedId(@Param("purchaseOrderId") int inventoryLevelId);


}
