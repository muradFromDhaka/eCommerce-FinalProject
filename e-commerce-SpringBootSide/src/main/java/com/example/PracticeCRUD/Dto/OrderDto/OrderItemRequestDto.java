package com.example.PracticeCRUD.Dto.OrderDto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderItemRequestDto {
    private Long productId;
    private int quantity;
}
