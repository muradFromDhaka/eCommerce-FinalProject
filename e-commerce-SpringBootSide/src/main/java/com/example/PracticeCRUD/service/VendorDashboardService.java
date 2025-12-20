package com.example.PracticeCRUD.service;

import com.example.PracticeCRUD.Dto.VendorDto.VendorDashboardDto;
import com.example.PracticeCRUD.Dto.VendorOrderDto.VendorOrderResponseDto;
import com.example.PracticeCRUD.Dto.VendorPayoutDto.VendorPayoutResponseDto;
import com.example.PracticeCRUD.Dto.VendorProfileDto.VendorProfileResponseDto;
import com.example.PracticeCRUD.Mapper.VendorOrderMapper;
import com.example.PracticeCRUD.Mapper.VendorPayoutMapper;
import com.example.PracticeCRUD.Mapper.VendorProfileMapper;
import com.example.PracticeCRUD.entity.Vendor;
import com.example.PracticeCRUD.entity.VendorOrder;
import com.example.PracticeCRUD.entity.VendorPayout;
import com.example.PracticeCRUD.entity.VendorProfile;
import com.example.PracticeCRUD.repository.VendorOrderRepository;
import com.example.PracticeCRUD.repository.VendorPayoutRepository;
import com.example.PracticeCRUD.repository.VendorProfileRepository;
import com.example.PracticeCRUD.repository.VendorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VendorDashboardService {

    private final VendorRepository vendorRepo;
    private final VendorProfileRepository profileRepo;
    private final VendorOrderRepository orderRepo;
    private final VendorPayoutRepository payoutRepo;

    public VendorDashboardDto getVendorDashboard(Long vendorId, int page, int size) {

        Vendor vendor = vendorRepo.findById(vendorId)
                .orElseThrow(() -> new RuntimeException("Vendor not found"));

        // --- Profile ---
        VendorProfile profile = profileRepo.findByVendorId(vendorId)
                .orElse(null);
        VendorProfileResponseDto profileDto = VendorProfileMapper.toResponseDto(profile);

        // --- Orders (pagination) ---
        Page<VendorOrder> orderPage = orderRepo.findByVendorId(vendorId, PageRequest.of(page, size));
        List<VendorOrderResponseDto> orderDtos = orderPage.stream()
                .map(VendorOrderMapper::toResponseDto)
                .collect(Collectors.toList());

        // --- Payouts (pagination) ---
        Page<VendorPayout> payoutPage = payoutRepo.findByVendorId(vendorId, PageRequest.of(page, size));
        List<VendorPayoutResponseDto> payoutDtos = payoutPage.stream()
                .map(VendorPayoutMapper::toResponseDto)
                .collect(Collectors.toList());

        // --- Aggregates ---
        BigDecimal totalEarned = orderRepo.findByVendorId(vendorId).stream()
                .filter(o -> o.getStatus() != null && o.getStatus().name().equals("COMPLETED"))
                .map(VendorOrder::getSubtotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal pendingPayout = payoutRepo.findByVendorId(vendorId).stream()
                .filter(p -> p.getStatus() != null && p.getStatus().name().equals("PENDING"))
                .map(VendorPayout::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        // --- Dashboard DTO ---
        VendorDashboardDto dashboardDto = new VendorDashboardDto();
        dashboardDto.setProfile(profileDto);
        dashboardDto.setOrders(orderDtos);
        dashboardDto.setPayouts(payoutDtos);
        dashboardDto.setTotalEarned(totalEarned);
        dashboardDto.setPendingPayout(pendingPayout);

        return dashboardDto;
    }
}
