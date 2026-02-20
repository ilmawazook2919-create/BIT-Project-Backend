package com.edu.Institiute.repo;



import com.edu.Institiute.entity.Bin;
import com.edu.Institiute.entity.InventoryLevel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface InventoryLevelRepo extends JpaRepository<InventoryLevel,String> {

    @Query(value = "SELECT * FROM inventoryLevel WHERE id=:inventoryLevelId", nativeQuery = true)
    InventoryLevel getInventoryLevelByProvideID(@Param("inventoryLevelId") int inventoryLevelId);

    @Query(value = "SELECT * FROM inventoryLevel WHERE id=:inventoryLevelId", nativeQuery = true)
    InventoryLevel findByInventoryLevelId(@Param("inventoryLevelId") int inventoryLevelId);

    @Query(value = "SELECT * FROM inventoryLevel WHERE id=:inventoryLevelId", nativeQuery = true)
    List<InventoryLevel> getAllInventoryLevel();

    @Query(value = "SELECT * FROM bin WHERE id=:binId", nativeQuery = true)
    List<InventoryLevel>getAllInventoryLevelForProvidedId(@Param("inventoryLevelId") int inventoryLevelId);
}
