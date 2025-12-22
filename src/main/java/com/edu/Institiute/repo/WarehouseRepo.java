package com.edu.Institiute.repo;


import com.edu.Institiute.entity.Customer;
import com.edu.Institiute.entity.Warehouse;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface WarehouseRepo extends CrudRepository<Warehouse, Integer> {
    @Query(value = "SELECT * FROM warehouse  WHERE id=:WarehouseId", nativeQuery = true)
    Warehouse getWarehouseByProvideId(@Param("warehouseId") String warehouseId);

    @Query(value = "SELECT * FROM warehouse WHERE id=:WarehouseId", nativeQuery = true)
    List<Warehouse> getAllWarehouse(@Param("WarehouseId") String warehouseId);

    @Query(value = "SELECT * FROM warehouse WHERE id=:warehouseId", nativeQuery = true)
    Warehouse findByWarehouseId(@Param("warehouseId") String warehouseId);

}
