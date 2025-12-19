package com.example.PracticeCRUD.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.PracticeCRUD.entity.VendorProfile;

public interface VendorProfileRepository extends JpaRepository<VendorProfile, Long>{

	Optional<VendorProfile> findByVendorId(Long vendorId);

}
