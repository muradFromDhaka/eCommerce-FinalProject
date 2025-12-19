package com.example.PracticeCRUD.Dto.VendorPayoutDto;

import com.example.PracticeCRUD.Enum.PayoutStatus;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class VendorPayoutRequestDto {

    private Long vendorId;
    private BigDecimal amount;
    private PayoutStatus status;       // PENDING, COMPLETED, FAILED
    private LocalDateTime payoutDate;  // Optional: for completed payouts
}
