package com.example.PracticeCRUD.Mapper;

import com.example.PracticeCRUD.Dto.VendorDto.VendorRequestDto;
import com.example.PracticeCRUD.Dto.VendorDto.VendorResponseDto;
import com.example.PracticeCRUD.entity.User;
import com.example.PracticeCRUD.entity.Vendor;

public class VendorMapper {

    // RequestDTO → Entity
    public static Vendor toEntity(VendorRequestDto dto, User user) {
        if (dto == null) return null;

        Vendor vendor = new Vendor();
        vendor.setShopName(dto.getShopName());
        vendor.setSlug(dto.getSlug());
        vendor.setDescription(dto.getDescription());
        vendor.setStatus(dto.getStatus() != null ? dto.getStatus() : vendor.getStatus());
        vendor.setUser(user);

        return vendor;
    }

    // Entity → ResponseDTO
    public static VendorResponseDto toResponseDto(Vendor vendor) {
        if (vendor == null) return null;

        VendorResponseDto dto = new VendorResponseDto();
        dto.setId(vendor.getId());
        dto.setShopName(vendor.getShopName());
        dto.setSlug(vendor.getSlug());
        dto.setDescription(vendor.getDescription());
        dto.setStatus(vendor.getStatus());
        dto.setRating(vendor.getRating());

        if (vendor.getUser() != null) {
            dto.setUserId(vendor.getUser().getId());
            dto.setUserName(vendor.getUser().getName());
        }

        return dto;
    }
}
