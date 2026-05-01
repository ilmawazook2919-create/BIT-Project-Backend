package com.edu.Institiute.utill.mapper;

import com.edu.Institiute.dto.GoodsReceivedNoteDto;
import com.edu.Institiute.entity.GoodsReceivedNote;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper(componentModel = "spring")
public interface GoodsReceivedNoteMapper {
    GoodsReceivedNote dtoToGoodsReceivedNoteEntity(GoodsReceivedNoteDto goodsReceivedNoteDto);
    GoodsReceivedNoteDto toGoodsReceivedNoteDto(GoodsReceivedNote goodsReceivedNote);
}
