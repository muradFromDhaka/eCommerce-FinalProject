package com.example.PracticeCRUD.service;

import com.example.PracticeCRUD.Dto.VendorOrderDto.VendorOrderRequestDto;
import com.example.PracticeCRUD.Dto.VendorOrderDto.VendorOrderResponseDto;
import com.example.PracticeCRUD.Mapper.VendorOrderMapper;
import com.example.PracticeCRUD.entity.Order;
import com.example.PracticeCRUD.entity.Vendor;
import com.example.PracticeCRUD.entity.VendorOrder;
import com.example.PracticeCRUD.repository.OrderRepository;
import com.example.PracticeCRUD.repository.VendorOrderRepository;
import com.example.PracticeCRUD.repository.VendorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VendorOrderService {

    private final VendorOrderRepository vendorOrderRepo;
    private final OrderRepository orderRepo;
    private final VendorRepository vendorRepo;

    public VendorOrderResponseDto create(VendorOrderRequestDto dto) {

        Order order = orderRepo.findById(dto.getOrderId())
                .orElseThrow(() -> new RuntimeException("Order not found"));

        Vendor vendor = vendorRepo.findById(dto.getVendorId())
                .orElseThrow(() -> new RuntimeException("Vendor not found"));

        VendorOrder vendorOrder =
                VendorOrderMapper.toEntity(dto, order, vendor);

        vendorOrderRepo.save(vendorOrder);

        return VendorOrderMapper.toResponseDto(vendorOrder);
    }

    public VendorOrderResponseDto getById(Long id) {
        VendorOrder vo = vendorOrderRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("VendorOrder not found"));
        return VendorOrderMapper.toResponseDto(vo);
    }

    public List<VendorOrderResponseDto> getByOrderId(Long orderId) {
        return vendorOrderRepo.findByOrderId(orderId)
                .stream()
                .map(VendorOrderMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    public List<VendorOrderResponseDto> getByVendorId(Long vendorId) {
        return vendorOrderRepo.findByVendorId(vendorId)
                .stream()
                .map(VendorOrderMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    public VendorOrderResponseDto updateStatus(Long id, VendorOrderRequestDto dto) {
        VendorOrder vo = vendorOrderRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("VendorOrder not found"));

        vo.setStatus(dto.getStatus());
        vendorOrderRepo.save(vo);

        return VendorOrderMapper.toResponseDto(vo);
    }
}
