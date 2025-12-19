package com.example.PracticeCRUD.Mapper;

import com.example.PracticeCRUD.Dto.DealDto.DealCreateDto;
import com.example.PracticeCRUD.Dto.DealDto.DealResponseDto;
import com.example.PracticeCRUD.Dto.DealDto.DealUpdateDto;
import com.example.PracticeCRUD.entity.Deal;
import com.example.PracticeCRUD.entity.Product;

public class DealMapper {

    // Create DTO → Entity
    public static Deal toEntity(DealCreateDto dto, Product product) {
        Deal deal = new Deal();
        deal.setTitle(dto.getTitle());
        deal.setDiscountPercent(dto.getDiscountPercent());
        deal.setStartTime(dto.getStartTime());
        deal.setEndTime(dto.getEndTime());
        deal.setProduct(product);
        return deal;
    }

    // Entity → Response DTO
    public static DealResponseDto toDto(Deal deal) {
        DealResponseDto dto = new DealResponseDto();
        dto.setId(deal.getId());
        dto.setTitle(deal.getTitle());
        dto.setDiscountPercent(deal.getDiscountPercent());
        dto.setStartTime(deal.getStartTime());
        dto.setEndTime(deal.getEndTime());

        dto.setProductId(deal.getProduct().getId());
        dto.setProductName(deal.getProduct().getName());
        return dto;
    }
    
    
 // Update DTO → Existing Entity
    public static void updateEntity(DealUpdateDto dto, Deal deal) {

        if (dto.getTitle() != null) {
            deal.setTitle(dto.getTitle());
        }

        if (dto.getDiscountPercent() != null) {
            deal.setDiscountPercent(dto.getDiscountPercent());
        }

        if (dto.getStartTime() != null) {
            deal.setStartTime(dto.getStartTime());
        }

        if (dto.getEndTime() != null) {
            deal.setEndTime(dto.getEndTime());
        }
    }

}

