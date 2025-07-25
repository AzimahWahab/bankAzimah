package com.bank.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bank.entity.ProductEntity;

public interface IProductRepo extends JpaRepository<ProductEntity, Long> {
}
