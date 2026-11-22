package com.edu.Institiute.repo;

import com.edu.Institiute.entity.InventoryLevel;
import com.edu.Institiute.entity.PurchaseOrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PurchaseOrderItemRepo extends JpaRepository<PurchaseOrderItem, Integer> {
    @Query(value = "SELECT * FROM purchase_order_item WHERE id=:purchaseOrderItemId", nativeQuery = true)
    PurchaseOrderItem getPurchaseOrderItemByProvideID(@Param("purchaseOrderItemId") int purchaseOrderItemId);

    @Query(value = "SELECT * FROM purchase_order_item WHERE id=:purchaseOrderItemId", nativeQuery = true)
    PurchaseOrderItem findByPurchaseOrderItemId(@Param("purchaseOrderItemId") int purchaseOrderItemId);

    @Query(value = "SELECT * FROM purchase_order_item", nativeQuery = true)
    List<PurchaseOrderItem> getAllPurchaseOrderItem();

    @Query(value = "SELECT * FROM purchase_order_item WHERE id=:purchaseOrderItemId", nativeQuery = true)
    List<PurchaseOrderItem>getAllPurchaseOrderItemForProvidedId(@Param("purchaseOrderItemId") int PurchaseOrderId);

    @Query(value = "SELECT * FROM purchase_order_item WHERE purchase_Order_Id=:purchaseOrderId", nativeQuery = true)
    List<PurchaseOrderItem> findByPurchaseOrderId(@Param("purchaseOrderId") int purchaseOrderId);

}
