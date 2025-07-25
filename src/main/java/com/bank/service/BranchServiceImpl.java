package com.bank.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.bank.entity.BranchEntity;
import com.bank.repo.IBranchRepo;
import com.bank.service.IBranchService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BranchServiceImpl implements IBranchService {

    private final IBranchRepo branchRepo;

    @Override
    public BranchEntity createBranch(BranchEntity branch) {
        if (branch.getBranchName() == null || branch.getBranchName().trim().isEmpty()) {
           // throw new DemoAppException("Branch Name cannot be empty or contain only spaces");
        	return null;
        }

        return branchRepo.save(branch);
    }

    @Override
    public BranchEntity getBranchById(Long id) {
        Optional<BranchEntity> branchOpt = branchRepo.findById(id);
        //return branchOpt.orElseThrow(() -> new DemoAppException("Branch with ID " + id + " not found"));
        return null;
    }

    @Override
    public List<BranchEntity> getAllBranches() {
        return branchRepo.findAll();
    }

    @Override
    public void deleteBranch(Long id) {
        if (!branchRepo.existsById(id)) {
            //throw new DemoAppException("Cannot delete. Branch with ID " + id + " does not exist.");
        	
        }
        branchRepo.deleteById(id);
    }

    @Override
    public List<BranchEntity> searchByBranchName(String name) {
        return branchRepo.findByBranchNameContainingIgnoreCase(name);
    }

    @Override
    public List<BranchEntity> searchByDateRange(LocalDateTime from, LocalDateTime to) {
        return branchRepo.findByCreationDateBetween(from, to);
    }

    @Override
    public List<BranchEntity> searchBranchByCreationDateBetween(LocalDateTime from, LocalDateTime to) {
        // Same as searchByDateRange — choose only one to avoid duplication
        return branchRepo.findByCreationDateBetween(from, to);
    }
}
