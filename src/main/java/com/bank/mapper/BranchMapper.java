package com.bank.mapper;

import com.bank.entity.BranchEntity;
import com.bank.model.BranchDTO;

import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface BranchMapper {

    BranchDTO toDto(BranchEntity entity);

    BranchEntity toEntity(BranchDTO dto);

    List<BranchDTO> toDtoList(List<BranchEntity> entityList);

    List<BranchEntity> toEntityList(List<BranchDTO> dtoList);
}