package com.example.PracticeCRUD.Dto.VendorPayoutDto;

import com.example.PracticeCRUD.Enum.PayoutStatus;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class VendorPayoutResponseDto {

    private Long id;
    private Long vendorId;
    private String vendorName; // shopName from Vendor
    private BigDecimal amount;
    private PayoutStatus status;
    private LocalDateTime payoutDate;
}
