package com.edu.Institiute.repo;

import com.edu.Institiute.entity.PickingList;
import com.edu.Institiute.entity.SalesOrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PickingListRepo  extends JpaRepository<PickingList, Integer> {

    @Query(value = "SELECT * FROM picking_list WHERE id=:pickingListId", nativeQuery = true)
    PickingList getPickingListByProvideId(@Param("pickingListId")int pickingListId);

    @Query(value = "SELECT * FROM picking_list WHERE id=:pickingListId", nativeQuery = true)
    PickingList findByPickingListId(@Param("pickingListId") int pickingListId);

    @Query(value = "SELECT * FROM picking_list WHERE id=:pickingListId", nativeQuery = true)
    List<PickingList> getAllPickingList();

    @Query(value = "SELECT * FROM picking_list WHERE id=:pickingListId", nativeQuery = true)
    List<PickingList>getAllPickingListForProvidedId(@Param("pickingListId") int  pickingListId);

}
