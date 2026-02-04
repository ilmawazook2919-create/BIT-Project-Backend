package com.edu.Institiute.repo;

import com.edu.Institiute.entity.Warehouse;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WarehouseRepo extends CrudRepository<Warehouse, Integer> {
    @Query(value = "SELECT * FROM warehouse  WHERE id=:warehouseId", nativeQuery = true)
    Warehouse getWarehouseByProvideId(@Param("warehouseId") String warehouseId);

    @Query(value = "SELECT * FROM warehouse WHERE id=:warehouseId", nativeQuery = true)
    List<Warehouse> getAllWarehouse(@Param("warehouseId") String warehouseId);

    @Query(value = "SELECT * FROM warehouse WHERE id=:warehouseId", nativeQuery = true)
    Warehouse findByWarehouseId(@Param("warehouseId") String warehouseId);

}
