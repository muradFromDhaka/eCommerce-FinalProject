package com.example.PracticeCRUD.controller;

import com.example.PracticeCRUD.Dto.DealDto.DealCreateDto;
import com.example.PracticeCRUD.Dto.DealDto.DealResponseDto;
import com.example.PracticeCRUD.Dto.DealDto.DealUpdateDto;
import com.example.PracticeCRUD.service.DealService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/deals")
@CrossOrigin(origins = "*")
public class DealController {

    
    private final DealService dealService;

    // Create a new Deal
    @PostMapping
    public ResponseEntity<DealResponseDto> createDeal(@RequestBody DealCreateDto dto) {
        DealResponseDto response = dealService.createDeal(dto);
        return ResponseEntity.ok(response);
    }

    // Get Deal by ID
    @GetMapping("/{id}")
    public ResponseEntity<DealResponseDto> getDealById(@PathVariable Long id) {
        DealResponseDto response = dealService.getDealById(id);
        return ResponseEntity.ok(response);
    }

    // Get all Deals
    @GetMapping
    public ResponseEntity<List<DealResponseDto>> getAllDeals() {
        List<DealResponseDto> responseList = dealService.getAllDeals();
        return ResponseEntity.ok(responseList);
    }

    // Update existing Deal
    @PutMapping("/{id}")
    public ResponseEntity<DealResponseDto> updateDeal(@PathVariable Long id, @RequestBody DealUpdateDto dto) {
        DealResponseDto response = dealService.updateDeal(id, dto);
        return ResponseEntity.ok(response);
    }

    // Delete Deal
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDeal(@PathVariable Long id) {
        dealService.deleteDeal(id);
        return ResponseEntity.noContent().build();
    }
}
