package com.edu.Institiute.repo;
import com.edu.Institiute.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface CustomerRepo extends JpaRepository<Customer,Integer> {

    @Query(value = "SELECT * FROM customer WHERE id=:customerId", nativeQuery = true)
    Customer  getCustomerByProvideID(@Param("customerId") String customerId);

    @Query(value = "SELECT * FROM customer WHERE id=:customerId", nativeQuery = true)
    List<Customer> getAllCustomer(@Param("customerId") String customerId);

    @Query(value = "SELECT * FROM customer WHERE id=:customerId", nativeQuery = true)
    Customer findByCustomerId(@Param("customerId") String customerId);
}
