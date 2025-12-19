package com.example.PracticeCRUD.Dto.OrderDto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemDto {
    private Long id;

    private Long productId;
    private String productName;

    private Long vendorId;
    private String vendorName;

    private int quantity;
    private BigDecimal unitPrice;
    private BigDecimal totalPrice;
}

