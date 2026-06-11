package com.edu.Institiute.repo;

import com.edu.Institiute.entity.GoodsReceivedNote;
import com.edu.Institiute.entity.SalesOrder;
import com.edu.Institiute.entity.Status;
import com.edu.Institiute.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, String> {
    @Query(value = "SELECT * FROM user WHERE id=userId", nativeQuery = true)
    User getUserByProvideId(@Param("userId")String userId);

}