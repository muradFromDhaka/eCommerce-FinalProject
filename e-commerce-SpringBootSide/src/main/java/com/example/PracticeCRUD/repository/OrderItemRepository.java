package com.example.PracticeCRUD.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.PracticeCRUD.entity.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long>{

	
}
