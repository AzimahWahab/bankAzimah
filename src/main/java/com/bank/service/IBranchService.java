package com.bank.service;

import com.bank.entity.BranchEntity;
import java.time.LocalDateTime;
import java.util.List;

public interface IBranchService {
    BranchEntity createBranch(BranchEntity branch);
    BranchEntity getBranchById(Long id);
    List<BranchEntity> getAllBranches();
    void deleteBranch(Long id);
    List<BranchEntity> searchByBranchName(String name);
    List<BranchEntity> searchByDateRange(LocalDateTime from, LocalDateTime to);
	List<BranchEntity> searchBranchByCreationDateBetween(LocalDateTime from, LocalDateTime to);
	List<BranchEntity> searchBranchByName(String name);
}





