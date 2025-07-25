package com.bank.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bank.mapper.BranchMapper;
import com.bank.model.BranchDTO;
import com.bank.service.IBranchService;
import com.bank.validation.BranchValidation;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/api/branches")
public class BranchController {

    private final IBranchService branchService;
    private final BranchMapper branchMapper;

    // Get all branches
    @GetMapping
    public ResponseEntity<List<BranchDTO>> getAllBranches() {
        return ResponseEntity.ok(
            branchMapper.toDtoList(branchService.getAllBranches())
        );
    }

    // Get branch by ID
    @GetMapping("/{id}")
    public ResponseEntity<BranchDTO> getBranchById(@PathVariable Long id) {
        return ResponseEntity.ok(
            branchMapper.toDto(branchService.getBranchById(id))
        );
    }

    // Create new branch with validation
    @PostMapping
    public ResponseEntity<BranchDTO> createBranch(@RequestBody BranchDTO branchDTO) {
        BranchValidation.validateBranchName(branchDTO.getBranchName());

        return ResponseEntity.ok(
            branchMapper.toDto(
                branchService.createBranch(branchMapper.toEntity(branchDTO))
            )
        );
    }

    // Delete branch by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBranch(@PathVariable Long id) {
        branchService.deleteBranch(id);
        return ResponseEntity.noContent().build();
    }

    // Search branch by name (case-insensitive contains)
    @GetMapping("/search")
    public ResponseEntity<List<BranchDTO>> searchByBranchName(@RequestParam String name) {
        return ResponseEntity.ok(
            branchMapper.toDtoList(branchService.searchBranchByName(name))
        );
    }

    // Search branch by creation date between
    @GetMapping("/search-by-date")
    public ResponseEntity<List<BranchDTO>> searchByCreationDateRange(
            @RequestParam LocalDateTime from,
            @RequestParam LocalDateTime to) {
        return ResponseEntity.ok(
            branchMapper.toDtoList(branchService.searchBranchByCreationDateBetween(from, to))
        );
    }
}