package com.bank.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.bank.entity.BranchEntity;
import com.bank.model.BranchDTO;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
class BranchMapperTest {

    @Autowired
    private BranchMapper branchMapper;

    @Test
    void testEntityToDtoAndBack() {
        // Create a sample BranchEntity
        BranchEntity entity = new BranchEntity();
        entity.setBranchID(101L);
        entity.setBranchName("Main Branch");
        entity.setBranchPostCode("75000");
        entity.setCreationDate(LocalDateTime.now());

        // Convert to DTO
        BranchDTO dto = branchMapper.toDto(entity);
        assertNotNull(dto);
        assertEquals(entity.getBranchID(), dto.getBranchID());
        assertEquals(entity.getBranchName(), dto.getBranchName());
        assertEquals(entity.getBranchPostCode(), dto.getBranchPostCode());

        // Convert back to Entity
        BranchEntity convertedEntity = branchMapper.toEntity(dto);
        assertNotNull(convertedEntity);
        assertEquals(dto.getBranchID(), convertedEntity.getBranchID());
        assertEquals(dto.getBranchName(), convertedEntity.getBranchName());
        assertEquals(dto.getBranchPostCode(), convertedEntity.getBranchPostCode());
    }
}