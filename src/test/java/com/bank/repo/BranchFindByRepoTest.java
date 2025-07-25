package com.bank.repo;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDateTime;
import java.util.List;

import com.bank.entity.BranchEntity;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test") // Uses application-test.properties for H2 setup
public class BranchFindByRepoTest {

    @Autowired
    private IBranchRepo branchRepo;

    @Test
    void testFindByBranchNameContainingIgnoreCase() {
        // Arrange
        BranchEntity branch = new BranchEntity();
        branch.setBranchName("Kuala Lumpur Central");
        branch.setBranchPostCode("50000");
        branch.setCreationDate(LocalDateTime.now());
        branchRepo.save(branch);

        // Act
        List<BranchEntity> result = branchRepo.findByBranchNameContainingIgnoreCase("lumpur");

        // Assert
        assertNotNull(result);
        assertFalse(result.isEmpty());
    }

    @Test
    void testFindByCreationDateBetween() {
        // Arrange
        LocalDateTime now = LocalDateTime.now();
        BranchEntity branch1 = new BranchEntity(null, "Penang North", "10000", now.minusDays(7));
        BranchEntity branch2 = new BranchEntity(null, "Penang South", "11000", now.minusDays(2));
        branchRepo.saveAll(List.of(branch1, branch2));

        // Act
        List<BranchEntity> result = branchRepo.findByCreationDateBetween(now.minusDays(10), now);

        // Assert
        assertNotNull(result);
        assertFalse(result.isEmpty());
    }

}
