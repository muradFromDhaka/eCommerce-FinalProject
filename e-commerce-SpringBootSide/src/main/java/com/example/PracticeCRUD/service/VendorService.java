package com.example.PracticeCRUD.service;

import com.example.PracticeCRUD.Dto.VendorDto.VendorRequestDto;
import com.example.PracticeCRUD.Dto.VendorDto.VendorResponseDto;
import com.example.PracticeCRUD.Mapper.VendorMapper;
import com.example.PracticeCRUD.entity.User;
import com.example.PracticeCRUD.entity.Vendor;
import com.example.PracticeCRUD.repository.UserRepository;
import com.example.PracticeCRUD.repository.VendorRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VendorService {

    private final VendorRepository vendorRepo;
    private final UserRepository userRepo;

    public VendorResponseDto create(VendorRequestDto dto) {
        User user = userRepo.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Vendor vendor = VendorMapper.toEntity(dto, user);
        vendorRepo.save(vendor);

        return VendorMapper.toResponseDto(vendor);
    }

    public VendorResponseDto update(Long id, VendorRequestDto dto) {
        Vendor vendor = vendorRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Vendor not found"));

        if (dto.getShopName() != null) vendor.setShopName(dto.getShopName());
        if (dto.getSlug() != null) vendor.setSlug(dto.getSlug());
        if (dto.getDescription() != null) vendor.setDescription(dto.getDescription());
        if (dto.getStatus() != null) vendor.setStatus(dto.getStatus());

        // Optional: update user if needed
        if (dto.getUserId() != null) {
            User user = userRepo.findById(dto.getUserId())
                    .orElseThrow(() -> new RuntimeException("User not found"));
            vendor.setUser(user);
        }

        vendorRepo.save(vendor);
        return VendorMapper.toResponseDto(vendor);
    }

    public VendorResponseDto getById(Long id) {
        Vendor vendor = vendorRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Vendor not found"));

        return VendorMapper.toResponseDto(vendor);
    }

    public List<VendorResponseDto> getAll() {
        return vendorRepo.findAll()
                .stream()
                .map(VendorMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    public void delete(Long id) {
        Vendor vendor = vendorRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Vendor not found"));
        vendorRepo.delete(vendor);
    }
}
