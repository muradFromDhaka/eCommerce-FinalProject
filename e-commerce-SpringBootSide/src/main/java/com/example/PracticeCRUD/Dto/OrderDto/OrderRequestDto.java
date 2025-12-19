package com.example.PracticeCRUD.Dto.OrderDto;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderRequestDto {
    private Long userId;
    private List<OrderItemRequestDto> items;
}
