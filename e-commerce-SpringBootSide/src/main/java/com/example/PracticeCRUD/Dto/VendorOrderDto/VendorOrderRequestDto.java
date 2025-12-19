package com.example.PracticeCRUD.Dto.VendorOrderDto;

import java.math.BigDecimal;

import com.example.PracticeCRUD.Enum.VendorOrderStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VendorOrderRequestDto {

    private Long orderId;
    private Long vendorId;
    private BigDecimal subtotal;
    private VendorOrderStatus status;
}
