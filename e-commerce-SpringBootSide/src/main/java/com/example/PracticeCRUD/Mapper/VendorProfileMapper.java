package com.example.PracticeCRUD.Mapper;

import com.example.PracticeCRUD.Dto.VendorProfileDto.VendorProfileRequestDto;
import com.example.PracticeCRUD.Dto.VendorProfileDto.VendorProfileResponseDto;
import com.example.PracticeCRUD.entity.Vendor;
import com.example.PracticeCRUD.entity.VendorProfile;

public class VendorProfileMapper {

    // RequestDTO → Entity
    public static VendorProfile toEntity(VendorProfileRequestDto dto, Vendor vendor) {
        if (dto == null) return null;

        VendorProfile profile = new VendorProfile();
        profile.setVendor(vendor);
        profile.setBusinessEmail(dto.getBusinessEmail());
        profile.setPhone(dto.getPhone());
        profile.setAddress(dto.getAddress());
        profile.setLogoUrl(dto.getLogoUrl());
        profile.setBannerUrl(dto.getBannerUrl());

        return profile;
    }

    // Entity → ResponseDTO
    public static VendorProfileResponseDto toResponseDto(VendorProfile profile) {
        if (profile == null) return null;

        VendorProfileResponseDto dto = new VendorProfileResponseDto();
        dto.setId(profile.getId());
        dto.setBusinessEmail(profile.getBusinessEmail());
        dto.setPhone(profile.getPhone());
        dto.setAddress(profile.getAddress());
        dto.setLogoUrl(profile.getLogoUrl());
        dto.setBannerUrl(profile.getBannerUrl());

        if (profile.getVendor() != null) {
            dto.setVendorId(profile.getVendor().getId());
            dto.setVendorName(profile.getVendor().getShopName());
        }

        return dto;
    }
}

