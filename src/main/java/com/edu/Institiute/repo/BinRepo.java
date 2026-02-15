package com.edu.Institiute.repo;
import com.edu.Institiute.entity.Bin;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BinRepo extends CrudRepository<Bin,Integer> {

    @Query(value = "SELECT * FROM bin WHERE id=:binId", nativeQuery = true)
    Bin getBinByProvideID(@Param("binId") String binId);

    @Query(value = "SELECT * FROM bin WHERE id=:binId", nativeQuery = true)
    Bin findByBinId(@Param("binId") String binId);

    @Query(value = "SELECT * FROM bin WHERE id=:binId", nativeQuery = true)
    List<Bin> getAllBin(@Param("binId") String binId);

    @Query(value = "SELECT * FROM bin", nativeQuery = true)
    List<Bin> getAllBins();

    @Query(value = "SELECT * FROM bin WHERE id=:binId", nativeQuery = true)
    Optional<Bin> getBinByID(@Param("binId") String binId);
}
