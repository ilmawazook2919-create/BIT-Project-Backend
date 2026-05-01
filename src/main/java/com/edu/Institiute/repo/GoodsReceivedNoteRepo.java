package com.edu.Institiute.repo;

import com.edu.Institiute.entity.Customer;
import com.edu.Institiute.entity.GoodsReceivedNote;
import com.edu.Institiute.entity.InventoryLevel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GoodsReceivedNoteRepo  extends JpaRepository<GoodsReceivedNote, String> {
    @Query(value = "SELECT * FROM goodsReceivedNote WHERE id=:goodsReceivedNoteId", nativeQuery = true)
    GoodsReceivedNote getGoodsReceivedNoteByProvideID(@Param("goodsReceivedNoteId")String goodsReceivedNoteId);


    @Query(value = "SELECT * FROM goodsReceivedNote WHERE id=:goodsReceivedNoteId", nativeQuery = true)
    GoodsReceivedNote findByGoodsReceivedNoteId(@Param("goodsReceivedNoteId") String goodsReceivedNoteId);

    @Query(value = "SELECT * FROM goodsReceivedNote WHERE id=:goodsReceivedNoteId", nativeQuery = true)
    List<GoodsReceivedNote> getAllGoodsReceivedNote();

    @Query(value = "SELECT * FROM goodsReceivedNote WHERE id=:goodsReceivedNoteId", nativeQuery = true)
    List<GoodsReceivedNote>getAllGoodsReceivedNoteForProvidedId(@Param("goodsReceivedNoteId") String goodsReceivedNoteId);
}
