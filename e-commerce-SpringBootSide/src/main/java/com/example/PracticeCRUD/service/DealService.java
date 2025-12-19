package com.example.PracticeCRUD.service;


import com.example.PracticeCRUD.Dto.DealDto.DealCreateDto;
import com.example.PracticeCRUD.Dto.DealDto.DealResponseDto;
import com.example.PracticeCRUD.Dto.DealDto.DealUpdateDto;
import com.example.PracticeCRUD.entity.Deal;
import com.example.PracticeCRUD.entity.Product;
import com.example.PracticeCRUD.Mapper.DealMapper;
import com.example.PracticeCRUD.repository.DealRepository;
import com.example.PracticeCRUD.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DealService {

    private final DealRepository dealRepository;
    private final ProductRepository productRepository;

    public DealResponseDto createDeal(DealCreateDto dto) {
        // Validate Product
        Product product = productRepository.findById(dto.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found with ID: " + dto.getProductId()));

        // Validate start/end time
        if (dto.getStartTime().isAfter(dto.getEndTime())) {
            throw new RuntimeException("Start time must be before end time");
        }

        // Map DTO to Entity
        Deal deal = DealMapper.toEntity(dto, product);

        // Save Deal
        deal = dealRepository.save(deal);

        // Return response
        return DealMapper.toDto(deal);
    }


    // Get Deal by ID
    public DealResponseDto getDealById(Long id) {
        Deal deal = dealRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Deal not found with ID: " + id));
        return DealMapper.toDto(deal);
    }

    // Get all Deals
    public List<DealResponseDto> getAllDeals() {
        return dealRepository.findAll()
                .stream()
                .map(DealMapper::toDto)
                .collect(Collectors.toList());
    }

    // Update existing Deal
    public DealResponseDto updateDeal(Long id, DealUpdateDto dto) {
        Deal deal = dealRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Deal not found with ID: " + id));

        DealMapper.updateEntity(dto, deal);
        deal = dealRepository.save(deal);
        return DealMapper.toDto(deal);
    }

    // Delete Deal
    public void deleteDeal(Long id) {
        Deal deal = dealRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Deal not found with ID: " + id));
        dealRepository.delete(deal);
    }
}

