package com.example.PracticeCRUD.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.PracticeCRUD.entity.Deal;

public interface DealRepository extends JpaRepository<Deal, Long>{

}
