package com.example.PracticeCRUD.controller;

import com.example.PracticeCRUD.Dto.VendorOrderDto.VendorOrderRequestDto;
import com.example.PracticeCRUD.Dto.VendorOrderDto.VendorOrderResponseDto;
import com.example.PracticeCRUD.service.VendorOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vendor-orders")
@RequiredArgsConstructor
@CrossOrigin("*")
public class VendorOrderController {

    private final VendorOrderService vendorOrderService;

    @PostMapping
    public ResponseEntity<VendorOrderResponseDto> create(
            @RequestBody VendorOrderRequestDto dto) {
        return ResponseEntity.ok(vendorOrderService.create(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<VendorOrderResponseDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(vendorOrderService.getById(id));
    }

    @GetMapping("/order/{orderId}")
    public ResponseEntity<List<VendorOrderResponseDto>> getByOrderId(
            @PathVariable Long orderId) {
        return ResponseEntity.ok(vendorOrderService.getByOrderId(orderId));
    }

    @GetMapping("/vendor/{vendorId}")
    public ResponseEntity<List<VendorOrderResponseDto>> getByVendorId(
            @PathVariable Long vendorId) {
        return ResponseEntity.ok(vendorOrderService.getByVendorId(vendorId));
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<VendorOrderResponseDto> updateStatus(
            @PathVariable Long id,
            @RequestBody VendorOrderRequestDto dto) {
        return ResponseEntity.ok(vendorOrderService.updateStatus(id, dto));
    }
}
