package com.edu.Institiute.repo;
import com.edu.Institiute.entity.Bin;
import com.edu.Institiute.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface BinRepo extends CrudRepository<Bin,Integer> {


    @Query(value = "SELECT * FROM bin WHERE id=:binId", nativeQuery = true)
    Bin  getBinByProvideID(@Param("binId") String binId);

    @Query(value = "SELECT * FROM bin WHERE id=:binId", nativeQuery = true)
    Bin findByBinId(@Param("binId") String binId);

    @Query(value = "SELECT * FROM bin WHERE id=:binId", nativeQuery = true)
    List<Bin> getAllBin(@Param("binId") String binId);

    @Query(value = "SELECT * FROM bin", nativeQuery = true)
    List<Bin> getAllBins();
}
