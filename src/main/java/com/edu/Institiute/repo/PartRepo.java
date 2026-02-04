package com.edu.Institiute.repo;
import com.edu.Institiute.entity.Part;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PartRepo extends JpaRepository<Part,Integer> {

    @Query(value = "SELECT * FROM  part WHERE id=:partId", nativeQuery = true)
    Part getPartByProvideID(@Param("partId") String partId);

    @Query(value = "SELECT * FROM  part WHERE id=:partId", nativeQuery = true)
    List<Part> getAllPart(@Param("partId") String partId);

    @Query(value = "SELECT * FROM  part WHERE id=:partId", nativeQuery = true)
    Part findByPartId(@Param("partId") String partId);
}
