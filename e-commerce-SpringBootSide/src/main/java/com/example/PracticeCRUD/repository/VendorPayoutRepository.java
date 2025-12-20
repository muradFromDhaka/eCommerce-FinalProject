package com.example.PracticeCRUD.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.PracticeCRUD.entity.VendorPayout;

public interface VendorPayoutRepository extends JpaRepository<VendorPayout, Long>{

	Optional<VendorPayout> findByVendorId(Long vendorId);

	Page<VendorPayout> findByVendorId(Long vendorId, PageRequest of);

}
