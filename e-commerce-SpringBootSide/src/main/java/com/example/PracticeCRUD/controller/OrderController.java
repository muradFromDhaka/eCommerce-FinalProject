package com.example.PracticeCRUD.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.PracticeCRUD.Dto.OrderDto.OrderDto;
import com.example.PracticeCRUD.Dto.OrderDto.OrderRequestDto;
import com.example.PracticeCRUD.service.OrderService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class OrderController {

    private final OrderService orderService;

    // ==========================
    // PLACE ORDER
    // ==========================
    @PostMapping
    public ResponseEntity<OrderDto> placeOrder(
            @RequestBody OrderRequestDto request) {

        OrderDto response = orderService.placeOrder(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // ==========================
    // GET ORDER BY ID
    // ==========================
    @GetMapping("/{orderId}")
    public ResponseEntity<OrderDto> getOrderById(
            @PathVariable Long orderId) {

        return ResponseEntity.ok(orderService.getOrderById(orderId));
    }

    // ==========================
    // GET ORDERS BY USER
    // ==========================
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<OrderDto>> getOrdersByUser(
            @PathVariable Long userId) {

        return ResponseEntity.ok(orderService.getOrdersByUser(userId));
    }
}
