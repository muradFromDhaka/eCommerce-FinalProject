package com.example.PracticeCRUD.Dto.VendorDto;

import java.math.BigDecimal;
import java.util.List;

import com.example.PracticeCRUD.Dto.VendorOrderDto.VendorOrderResponseDto;
import com.example.PracticeCRUD.Dto.VendorPayoutDto.VendorPayoutResponseDto;
import com.example.PracticeCRUD.Dto.VendorProfileDto.VendorProfileResponseDto;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class VendorDashboardDto {
	VendorProfileResponseDto profile;
    List<VendorOrderResponseDto> orders;       // Pagination possible
    List<VendorPayoutResponseDto> payouts;     // Pagination possible
    BigDecimal totalEarned;                    // sum of completed VendorOrders
    BigDecimal pendingPayout;   
}

