package com.edu.Institiute.utill.mapper;

import com.edu.Institiute.dto.GoodsReceivedNoteItemDto;
import com.edu.Institiute.entity.GoodsReceivedNoteItem;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper(componentModel = "spring")
public interface GoodsReceivedNoteItemMapper {
    GoodsReceivedNoteItem dtoToGoodsReceivedNoteItemEntity(GoodsReceivedNoteItemDto goodsReceivedNoteItemDto);
    GoodsReceivedNoteItemDto toGoodsReceivedNoteItemDto(GoodsReceivedNoteItem goodsReceivedNoteItem);
}
