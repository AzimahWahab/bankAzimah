package com.bank.controller;

import com.bank.model.BranchDTO;
import com.bank.service.IBranchService;
import com.bank.mapper.BranchMapper;

import lombok.AllArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

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
        if (branchDTO.getBranchName() == null || branchDTO.getBranchName().trim().isEmpty()) {
        throw new DemoAppException("Branch Name cannot be empty");
        }

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
        	branchMapper.toDtoList(branchService.searchByBranchName(name))
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