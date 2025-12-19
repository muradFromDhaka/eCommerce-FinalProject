package com.example.PracticeCRUD.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.PracticeCRUD.entity.OrderItem;
import com.example.PracticeCRUD.service.OrderItemService;


@RestController
@RequestMapping("/api/orderItems")
@CrossOrigin(origins = "*")
public class OrderItemController {

	@Autowired
	private OrderItemService service;
	
	@GetMapping
	public ResponseEntity<List<OrderItem>> getAllOrderItem(){
		return ResponseEntity.ok(service.getAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<OrderItem> getOrderItemById(@PathVariable Long id){
	    return service.getById(id)
	            .map(ResponseEntity::ok)
	            .orElse(ResponseEntity.notFound().build());
	}
	
	@PostMapping
	public ResponseEntity<OrderItem> saveOrderItem(@RequestBody OrderItem orderItem){
		return ResponseEntity.ok(service.save(orderItem));
	}
	
	@PutMapping("/updated/{id}")
	public ResponseEntity<OrderItem> updateOrderItem(@PathVariable Long id, @RequestBody OrderItem orderItem){
		return ResponseEntity.ok(service.update(id, orderItem));
	}
	
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Void> deleteOrderItem(@PathVariable Long id){
	    boolean deleted = service.delete(id);
	    return deleted ? ResponseEntity.noContent().build()
	                   : ResponseEntity.notFound().build();
	}
	
	
	
}
