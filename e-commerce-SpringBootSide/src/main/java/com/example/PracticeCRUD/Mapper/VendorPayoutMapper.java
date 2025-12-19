package com.example.PracticeCRUD.Mapper;

import com.example.PracticeCRUD.Dto.VendorPayoutDto.VendorPayoutRequestDto;
import com.example.PracticeCRUD.Dto.VendorPayoutDto.VendorPayoutResponseDto;
import com.example.PracticeCRUD.entity.Vendor;
import com.example.PracticeCRUD.entity.VendorPayout;

public class VendorPayoutMapper {

    // RequestDTO → Entity
    public static VendorPayout toEntity(VendorPayoutRequestDto dto, Vendor vendor) {
        if (dto == null) return null;

        VendorPayout payout = new VendorPayout();
        payout.setVendor(vendor);
        payout.setAmount(dto.getAmount());
        payout.setStatus(dto.getStatus());
        payout.setPayoutDate(dto.getPayoutDate());
        return payout;
    }

    // Entity → ResponseDTO
    public static VendorPayoutResponseDto toResponseDto(VendorPayout payout) {
        if (payout == null) return null;

        VendorPayoutResponseDto dto = new VendorPayoutResponseDto();
        dto.setId(payout.getId());
        dto.setAmount(payout.getAmount());
        dto.setStatus(payout.getStatus());
        dto.setPayoutDate(payout.getPayoutDate());

        if (payout.getVendor() != null) {
            dto.setVendorId(payout.getVendor().getId());
            dto.setVendorName(payout.getVendor().getShopName());
        }

        return dto;
    }
}
