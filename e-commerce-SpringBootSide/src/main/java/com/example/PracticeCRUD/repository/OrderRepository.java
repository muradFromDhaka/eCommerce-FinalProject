package com.example.PracticeCRUD.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.PracticeCRUD.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long>{

	List<Order> findByUserId(Long userId);
}
