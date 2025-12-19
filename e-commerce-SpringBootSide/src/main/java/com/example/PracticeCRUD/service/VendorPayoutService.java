package com.example.PracticeCRUD.service;

import com.example.PracticeCRUD.Dto.VendorPayoutDto.VendorPayoutRequestDto;
import com.example.PracticeCRUD.Dto.VendorPayoutDto.VendorPayoutResponseDto;
import com.example.PracticeCRUD.Mapper.VendorPayoutMapper;
import com.example.PracticeCRUD.entity.Vendor;
import com.example.PracticeCRUD.entity.VendorPayout;
import com.example.PracticeCRUD.repository.VendorPayoutRepository;
import com.example.PracticeCRUD.repository.VendorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VendorPayoutService {

    private final VendorPayoutRepository payoutRepo;
    private final VendorRepository vendorRepo;

    public VendorPayoutResponseDto createPayout(VendorPayoutRequestDto dto) {

        Vendor vendor = vendorRepo.findById(dto.getVendorId())
                .orElseThrow(() -> new RuntimeException("Vendor not found"));

        VendorPayout payout = VendorPayoutMapper.toEntity(dto, vendor);

        payoutRepo.save(payout);

        return VendorPayoutMapper.toResponseDto(payout);
    }

    public VendorPayoutResponseDto getPayoutById(Long id) {
        VendorPayout payout = payoutRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Payout not found"));

        return VendorPayoutMapper.toResponseDto(payout);
    }

    public List<VendorPayoutResponseDto> getPayoutsByVendorId(Long vendorId) {
        return payoutRepo.findByVendorId(vendorId)
                .stream()
                .map(VendorPayoutMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    public VendorPayoutResponseDto updatePayoutStatus(Long id, VendorPayoutRequestDto dto) {
        VendorPayout payout = payoutRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Payout not found"));

        if (dto.getStatus() != null) payout.setStatus(dto.getStatus());
        if (dto.getPayoutDate() != null) payout.setPayoutDate(dto.getPayoutDate());

        payoutRepo.save(payout);

        return VendorPayoutMapper.toResponseDto(payout);
    }

    public void deletePayout(Long id) {
        if (!payoutRepo.existsById(id)) throw new RuntimeException("Payout not found");
        payoutRepo.deleteById(id);
    }
}
