package com.example.PracticeCRUD.Dto.VendorOrderDto;

import com.example.PracticeCRUD.Enum.VendorOrderStatus;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class VendorOrderResponseDto {

    private Long id;

    private Long orderId;
    private Long vendorId;
    private String vendorName;

    private BigDecimal subtotal;
    private VendorOrderStatus status;
}
