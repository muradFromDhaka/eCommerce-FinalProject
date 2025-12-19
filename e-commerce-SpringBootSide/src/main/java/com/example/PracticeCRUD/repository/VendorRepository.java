package com.example.PracticeCRUD.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.PracticeCRUD.entity.Vendor;

public interface VendorRepository extends JpaRepository<Vendor, Long>{

}
