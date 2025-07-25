package com.bank.repo;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.bank.entity.AccountEntity;
import com.bank.entity.CustomerEntity;
import com.bank.entity.ProductEntity;

//@SpringBootTest
//@ActiveProfiles("test") // Uses application-test.properties for H2 setup
class CustomerFetchEagerLoadTest {

	//TODO: SpringBoot: Practical Bonus 2 - Eager load vs Lazy Load
	
	// When one to many relation in the entity, default fetch is Lazy Load 
	
	// Enable the unit test
	// Run it and expect error at line of code of found.getAccountEntities().get(0)
	// Add Eager load to CustomerEntity
	
    @Autowired
    private ICustomerRepo customerRepo;
    
    @Autowired 
    private IProductRepo productRepo;
    
    @Autowired
    private IAccountRepo accountRepo;
    
	//@Test
    void testSaveCustomerWithAccount() {
        // Step 1: Create Customer
        CustomerEntity customer = new CustomerEntity();
        customer.setIcNumber("IC123456789");
        customer.setLastname("Tan");
        customer.setSurname("Ahmad");
        customer.setDescription("Test customer with account");
        customer.setCreationDate(LocalDateTime.now());

        // Step 4: Save customer (cascades account save)
        CustomerEntity savedCustomer = customerRepo.save(customer);
        assertNotNull(savedCustomer);
        CustomerEntity found = customerRepo.findById(savedCustomer.getCustomerID()).get();
        
        ProductEntity productEntity = new ProductEntity();
        productEntity.setProductName("name");
        productEntity.setProductID(1l);
        productRepo.save(productEntity);
        
        // Step 2: Create Account and associate with Customer
        AccountEntity account = new AccountEntity();
        account.setAccountNumber("ACC00112233");
        account.setBalance(5000.0);
        account.setCreationDate(LocalDateTime.now());
        account.setCustomerEntity(customer);

        account.setProductEntity(productEntity);
        account.setCustomerEntity(found);
        
        // Step 3: Add account to customer
        
        accountRepo.save(account);
        
        found = customerRepo.findById(savedCustomer.getCustomerID()).get();
        assertNotNull(found);
        assertNotNull(found.getAccountEntities().get(0)); // expected error here
    }
}
