package com.example.PracticeCRUD.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.PracticeCRUD.entity.VendorOrder;

public interface VendorOrderRepository extends JpaRepository<VendorOrder, Long>{

	Optional<VendorOrder> findByOrderId(Long orderId);
	Optional<VendorOrder> findByVendorId(Long vendorId);

}
