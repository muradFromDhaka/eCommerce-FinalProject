package com.example.PracticeCRUD.Mapper;

import com.example.PracticeCRUD.Dto.VendorOrderDto.VendorOrderRequestDto;
import com.example.PracticeCRUD.Dto.VendorOrderDto.VendorOrderResponseDto;
import com.example.PracticeCRUD.entity.Order;
import com.example.PracticeCRUD.entity.Vendor;
import com.example.PracticeCRUD.entity.VendorOrder;

public class VendorOrderMapper {

    // RequestDTO → Entity
    public static VendorOrder toEntity(
            VendorOrderRequestDto dto,
            Order order,
            Vendor vendor
    ) {
        VendorOrder vo = new VendorOrder();
        vo.setOrder(order);
        vo.setVendor(vendor);
        vo.setSubtotal(dto.getSubtotal());
        vo.setStatus(dto.getStatus());
        return vo;
    }

    // Entity → ResponseDTO
    public static VendorOrderResponseDto toResponseDto(VendorOrder vo) {
        VendorOrderResponseDto dto = new VendorOrderResponseDto();
        dto.setId(vo.getId());
        dto.setSubtotal(vo.getSubtotal());
        dto.setStatus(vo.getStatus());

        if (vo.getOrder() != null) {
            dto.setOrderId(vo.getOrder().getId());
        }

        if (vo.getVendor() != null) {
            dto.setVendorId(vo.getVendor().getId());
            dto.setVendorName(vo.getVendor().getShopName());
        }

        return dto;
    }
}
