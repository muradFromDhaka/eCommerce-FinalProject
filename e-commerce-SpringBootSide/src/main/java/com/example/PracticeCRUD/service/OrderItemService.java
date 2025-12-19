package com.example.PracticeCRUD.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.PracticeCRUD.entity.OrderItem;
import com.example.PracticeCRUD.repository.OrderItemRepository;


@Service
public class OrderItemService {

	@Autowired
	private OrderItemRepository repo;
	
	public List<OrderItem> getAll(){
		return repo.findAll();
	}
	
	
	public Optional<OrderItem> getById(Long id){
		return repo.findById(id);
	}
	
	
	
	public OrderItem save(OrderItem orderItem){
		return repo.save(orderItem);
	}
	
	public OrderItem update(Long id, OrderItem updateOrderItem){
		OrderItem existingOrderItem = repo.findById(id)
	            .orElseThrow(() -> new RuntimeException("Order not found with id " + id));
	    existingOrderItem.setQuantity(updateOrderItem.getQuantity());
	    existingOrderItem.setUnitPrice(updateOrderItem.getUnitPrice());

	    return repo.save(existingOrderItem);
	}

	
	
	public Boolean delete(Long id) {
		Optional<OrderItem> existing = repo.findById(id);
		
		if(existing.isPresent()) {
			repo.deleteById(id);
			return true;
		}else {
			return false;
		}
	}
	
	
}
