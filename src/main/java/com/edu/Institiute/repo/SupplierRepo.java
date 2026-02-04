package com.edu.Institiute.repo;

import com.edu.Institiute.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SupplierRepo extends JpaRepository<Supplier,Integer>{
    @Query(value = "SELECT * FROM supplier WHERE id=:supplierId", nativeQuery = true)
    Supplier getSupplierByProvideID(@Param("supplierId") String supplierId);
    @Query(value = "SELECT * FROM supplier WHERE id=:supplierId", nativeQuery = true)
    Supplier findBySupplierId(@Param("supplierId") String supplierId);
    @Query(value = "SELECT * FROM supplier WHERE id=:supplierId", nativeQuery = true)
    List<Supplier> getAllSupplier(@Param("supplierId") String supplierId);
}
