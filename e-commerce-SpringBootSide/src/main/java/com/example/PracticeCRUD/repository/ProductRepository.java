package com.example.PracticeCRUD.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.PracticeCRUD.entity.Product;


public interface ProductRepository extends JpaRepository<Product, Long>{
	
    List<Product> findByCategoryId(Long categoryId);

	List<Product> findByCategoryName(String name);

	List<Product> findProductsByName(String name);

	List<Product> findByPriceBetween(BigDecimal min, BigDecimal max);

	Page<Product> findByAvailableTrue(Pageable pageable);

    
}
