package com.edu.Institiute.repo;
import com.edu.Institiute.entity.Category;
import com.edu.Institiute.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepo extends CrudRepository<Category, Integer> {

    @Query(value = "SELECT * FROM category WHERE id=:categoryId", nativeQuery = true)
    Category getCategoryByProvideID(@Param("categoryId") String customerId);

    @Query(value = "SELECT * FROM category WHERE id=:categoryId", nativeQuery = true)
    List<Category> getAllCategory(@Param("categoryId") String categoryId);

    @Query(value = "SELECT * FROM category WHERE id=:categoryId", nativeQuery = true)
    Category findByCategoryId(@Param("categoryId") String categoryId);

    @Query(value = "SELECT * FROM category", nativeQuery = true)
    List<Category> getAllCategories();
    //

}
