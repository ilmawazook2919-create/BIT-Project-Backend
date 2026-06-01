package com.edu.Institiute.repo;
import com.edu.Institiute.entity.GoodsReceivedNoteItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GoodsReceivedNoteItemRepo extends JpaRepository<GoodsReceivedNoteItem, String> {
    @Query(value = "SELECT * FROM goodsReceivedNoteItem WHERE id=:goodsReceivedNoteItemId", nativeQuery = true)
    GoodsReceivedNoteItem getGoodsReceivedNoteItemByProvideId(@Param("goodsReceivedNoteItemId")String goodsReceivedNoteItemId);


    @Query(value = "SELECT * FROM goodsReceivedNoteItem WHERE id=:goodsReceivedNoteItemId", nativeQuery = true)
    GoodsReceivedNoteItem findByGoodsReceivedNoteItemId(@Param("goodsReceivedNoteItemId") String goodsReceivedNoteItemId);

    @Query(value = "SELECT * FROM goodsReceivedNoteItem WHERE id=:goodsReceivedNoteItemId", nativeQuery = true)
    List<GoodsReceivedNoteItem> getAllGoodsReceivedNoteItem();

    @Query(value = "SELECT * FROM goodsReceivedNoteItem WHERE id=:goodsReceivedNoteItemId", nativeQuery = true)
    List<GoodsReceivedNoteItem>getAllGoodsReceivedNoteItemForProvidedId(@Param("goodsReceivedNoteItemId") String goodsReceivedNoteItemId);
}

