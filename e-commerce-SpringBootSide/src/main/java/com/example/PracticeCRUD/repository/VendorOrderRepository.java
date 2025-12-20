package com.example.PracticeCRUD.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.PracticeCRUD.entity.VendorOrder;

public interface VendorOrderRepository extends JpaRepository<VendorOrder, Long>{

	Optional<VendorOrder> findByOrderId(Long orderId);
	Optional<VendorOrder> findByVendorId(Long vendorId);
	Page<VendorOrder> findByVendorId(Long vendorId, PageRequest of);

}
